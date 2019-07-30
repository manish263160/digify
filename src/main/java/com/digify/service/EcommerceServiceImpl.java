package com.digify.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.dao.EcommerceDao;
import com.digify.model.CartModel;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.UserCart;
import com.digify.model.UserOrderDetails;
import com.digify.model.UserOrderResponseDto;
import com.digify.model.UserOrders;
import com.digify.utils.EcommerceUtils;

@Service
public class EcommerceServiceImpl implements EcommerceService {

	private static final Logger logger = Logger.getLogger(EcommerceServiceImpl.class);
    @Autowired
    EcommerceDao ecommerceDao;

    @Autowired
    ProductService productService;

    @Override
    @Transactional
    public boolean addTocart(String cartFor, long userId, int serviceProductId, HttpServletRequest request) {

        Long keyInserted = ecommerceDao.addTocart(cartFor, userId, serviceProductId);

        UserCart myCart = EcommerceUtils.getCartInSession(request);
        if (myCart != null) {
            if (cartFor.equals(BASIC_STRINGS.PRODUCTS.getStringName())) {
            	List<Products> productsList =	new ArrayList<>();
                Products product = productService.getProductServiceById(serviceProductId , cartFor);
                if(myCart.getListOfProducts() != null && !myCart.getListOfProducts().isEmpty()) {               
                	productsList = myCart.getListOfProducts();
                }
                product.setUserCartProductPK(keyInserted);
                productsList.add(product);
              
                myCart.setListOfProducts(productsList);
                return true;
            } else if (cartFor.equals(BASIC_STRINGS.SERVICES.getStringName())) {
                List<Services> serviceList = new ArrayList<>();
                Services service = productService.getProductServiceById(serviceProductId , cartFor);
                if(myCart.getListOfService() != null && !myCart.getListOfService().isEmpty()) {               
                	serviceList = myCart.getListOfService();
                }
                service.setUserCartServicetPK(keyInserted);
                serviceList.add(service);
                myCart.setListOfService(serviceList);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<CartModel> listOfUserCart(long userId) {
        return ecommerceDao.listOfUserCart(userId);
    }

    @Override
    public UserCart getUserCart(long userId) {

        List<CartModel> listOfCart = listOfUserCart(userId);
        UserCart userCart = null;
        List<Services> listOfService = new ArrayList();
        List<Products> listOfProducts = new ArrayList();
        if (listOfCart != null) {
            for (CartModel cart : listOfCart) {
                if (cart.getCartFor().equals(BASIC_STRINGS.PRODUCTS.getStringName())) {
                    Products product = productService.getProductServiceById(cart.getProductId(), BASIC_STRINGS.PRODUCTS.getStringName());
                    product.setUserCartProductPK(Long.valueOf(cart.getId()));
                    listOfProducts.add(product);
                } else if (cart.getCartFor().equals(BASIC_STRINGS.SERVICES.getStringName())) {
                    Services service = productService.getProductServiceById(cart.getServiceId(), BASIC_STRINGS.SERVICES.getStringName());
                    service.setUserCartServicetPK(Long.valueOf(cart.getId()));
                    listOfService.add(service);
                }
            }
        }
        userCart = new UserCart();
        userCart.setListOfProducts(listOfProducts);
        userCart.setListOfService(listOfService);
        return userCart;
    }

	@Override
	@Transactional
	public boolean insertOrder(UserOrders userOrders , HttpServletRequest request) {
		logger.info("insertOrder is started===");
		UserCart myCart = EcommerceUtils.getCartInSession(request);
		Integer isinsertOrder = ecommerceDao.insertOrder(userOrders);
		List<Long> Ids = new ArrayList<Long>(); 
		if(isinsertOrder != null) {
			userOrders.setId(isinsertOrder.intValue());
			List<Products> productList = myCart.getListOfProducts();
			int[] intArr = {};
			if (productList != null && !productList.isEmpty()) {
				intArr = ecommerceDao.insertOrderDetails(BASIC_STRINGS.PRODUCTS.getStringName(), userOrders , productList);
			}
			List<Services> servicesList = myCart.getListOfService();
			if (servicesList != null && !servicesList.isEmpty()) {
				intArr = ecommerceDao.insertOrderDetails(BASIC_STRINGS.SERVICES.getStringName(), userOrders , servicesList);
			}
			if(intArr != null && intArr.length != 0) {
				ecommerceDao.cartDelete(userOrders.getUserId());
				EcommerceUtils.removeCartInSession(request);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeFromCart(int cartId) {
		boolean isdelete = false;
		isdelete = ecommerceDao.removeFromCart(cartId);
	return isdelete;
	}

	@Override
	public Map<String, List<UserOrderResponseDto>> getUserOrders() {
		
		List<UserOrders> userAllOrders =  ecommerceDao.getUserOrders(false);
			List<UserOrderDetails> listUserOrderDetail =  ecommerceDao.getUserOrdersDetails(null);
			List<UserOrderResponseDto> userOrderResponseDto = new LinkedList<>();
			Map<String, List<UserOrderResponseDto>> returnDto = new LinkedHashMap<>();
			
			for (UserOrderDetails userOrdersDetails : listUserOrderDetail) {
				UserOrderResponseDto dto = new UserOrderResponseDto();
				String orderId = userOrdersDetails.getUserOrderId();
				
				Long productId = userOrdersDetails.getProductId();
				Long serviceId = userOrdersDetails.getServiceId();
				
				if(productId != null) {
					Products product=  productService.getProductServiceById(productId, BASIC_STRINGS.PRODUCTS.getStringName());
					dto.setProducts(product);
				}
				if(serviceId != null) {
					Services service = productService.getProductServiceById(serviceId, BASIC_STRINGS.SERVICES.getStringName());
					dto.setServices(service);
				}
				dto.setUserOrderId(userOrdersDetails.getUserOrderId());
				dto.setCreatedOn(userOrdersDetails.getCreatedOn());
				dto.setTXNAMOUNT(userOrdersDetails.getTXNAMOUNT());
				dto.setTXNID(userOrdersDetails.getTXNID());
				userOrderResponseDto.add(dto);
				
			}
			for (UserOrders userOrdersAll : userAllOrders) {
				List<UserOrderResponseDto> list = new ArrayList<>(); 
				for (UserOrderResponseDto userOrdrResponsedto : userOrderResponseDto) {
					if(userOrdrResponsedto.getTXNID().equals(userOrdersAll.getTxnid())) {
						list.add(userOrdrResponsedto);
					}
				}
				returnDto.put(userOrdersAll.getTxnid(), list);
			}
		return returnDto;
	}
}
