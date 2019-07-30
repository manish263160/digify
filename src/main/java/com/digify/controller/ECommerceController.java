package com.digify.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.Enums.CommonEnums;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.User;
import com.digify.model.UserCart;
import com.digify.model.UserOrders;
import com.digify.service.EcommerceService;
import com.digify.service.ProductService;
import com.digify.service.UserService;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.EcommerceUtils;
import com.digify.utils.GenUtilities;
import com.paytm.pg.merchant.CheckSumServiceHelper;

/**
 * @author Manish Kumar Mandal
 */
@Controller
@RequestMapping(value = { "/ecommerce" })
@PreAuthorize("hasRole('ROLE_USER')")
public class ECommerceController {

	private static final Logger logger = Logger.getLogger(ECommerceController.class);

	@Autowired
	EcommerceService ecommerceService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Value("${paytm.test.merchant.id}")
	private String MID;

	@Value("${paytm.test.account.secret.key}")
	private String secretKey;

	@Value("${paytm.url}")
	private String PAYTM_URL;

	@RequestMapping(value = { "/addTocart/{userId}/{serviceProductId}/{cartFor}" }, method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<Boolean> addToCart(@PathVariable("cartFor") String cartFor,
			@PathVariable("userId") int userId, @PathVariable("serviceProductId") int serviceProductId,
			HttpServletRequest request) {

		// cartfor is already set from ajax don't need to change the table here .

		logger.info("cartFor =" + cartFor + " userId=" + userId + " serviceProductId=" + serviceProductId);
		boolean cartInfo = ecommerceService.addTocart(cartFor, userId, serviceProductId, request);

		UserCart myCart = EcommerceUtils.getCartInSession(request);
		return new ResponseEntity<Boolean>(cartInfo, HttpStatus.OK);
	}

	@RequestMapping(value = { "/getUserCart/{userId}" }, method = { RequestMethod.GET })
	public String getUserCart(ModelMap model, @PathVariable("userId") int userId, HttpServletRequest request) {
//        UserCart userCart = ecommerceService.getUserCart(userId);
		UserCart myCart = EcommerceUtils.getCartInSession(request);
		int totalCost = 0;
		List<Products> productList = myCart.getListOfProducts();
		if (productList != null) {
			for (Products products : productList) {
				totalCost = totalCost + products.getCost();
			}
		}
		List<Services> servicesList = myCart.getListOfService();
		if (servicesList != null) {
			for (Services services : servicesList) {
				totalCost = totalCost + services.getCost();
			}
		}
		model = productService.setProductservice(model);
		model.addAttribute("productList", productList);
		model.addAttribute("servicesList", servicesList);
		model.addAttribute("totalCost", totalCost);
		return "frontend/userCart";
	}

	@RequestMapping(value = { "/checkOut/{userId}/{totalCost}" }, method = { RequestMethod.GET })
	public String checkoutPayment(ModelMap model, @PathVariable("userId") long userId,
			@PathVariable("totalCost") int totalCost, HttpServletRequest request) throws Exception {

		logger.debug("userId ==" + userId + " totalcost=" + totalCost);
		UserCart myCart = EcommerceUtils.getCartInSession(request);
		User user = userService.findById(userId);
		Random randomGenerator = new Random();
		int randomOrder = randomGenerator.nextInt(1000000);

//		String merchantMid = MID;
		// Key in your staging and production MID available in your dashboard
//		String merchantKey = secretKey;
		// Key in your staging and production merchant key available in your dashboard
		String orderId = "" + randomOrder;
		String channelId = "WEB";
		String custId = Long.toString(user.getId());
		String mobileNo = user.getMobileNo();
		String email = user.getEmail();
		String txnAmount = "100.12";
		String website = "WEBSTAGING";
		// This is the staging value. Production value is available in your dashboard
		String industryTypeId = "Retail";
		// This is the staging value. Production value is available in your dashboard
		String callbackUrl = applicationProperties.getProperty("appUrl") + "ecommerce/checkoutResponse";
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();
		paytmParams.put("MID", MID);
		paytmParams.put("ORDER_ID", orderId);
		paytmParams.put("CHANNEL_ID", channelId);
		paytmParams.put("CUST_ID", custId);
		paytmParams.put("MOBILE_NO", mobileNo);
		paytmParams.put("EMAIL", email);
		paytmParams.put("TXN_AMOUNT", txnAmount);
		paytmParams.put("WEBSITE", website);
		paytmParams.put("INDUSTRY_TYPE_ID", industryTypeId);
		paytmParams.put("CALLBACK_URL", callbackUrl);
		String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(secretKey, paytmParams);

		model.addAttribute("paytmChecksum", paytmChecksum);
		model.addAttribute("randomOrder", randomOrder);
		model.addAttribute("callbackUrl", callbackUrl);
		model.addAttribute("user", user);
		model.addAttribute("MID", MID);
		model.addAttribute("PAYTM_URL", PAYTM_URL);
		model.addAttribute("secretKey", secretKey);
		return "frontend/paytmPage";

	}

	@RequestMapping(value = { "/checkoutResponse" }, method = { RequestMethod.POST })
	public String checkoutResponse(HttpServletRequest request, ModelMap model) throws Exception {

		Enumeration<String> paramNames = request.getParameterNames();

		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		String paytmChecksum = "";
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if (paramName.equals("CHECKSUMHASH")) {
				paytmChecksum = mapData.get(paramName)[0];
			}
			parameters.put(paramName, mapData.get(paramName)[0]);
		}
		ModelMapper modelMapper = new ModelMapper();
		UserOrders userOrders = modelMapper.map(parameters, UserOrders.class);

		User user = GenUtilities.getLoggedInUser();
		if (user != null) {
			userOrders.setUserId(Integer.parseInt(user.getId() + ""));
			boolean isValideChecksum = false;
			String outputHTML = "";
			try {
				isValideChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(secretKey,
						parameters, paytmChecksum);
				if (isValideChecksum && parameters.containsKey("RESPCODE")) {
					if (parameters.get("RESPCODE").equals("01")) {
						outputHTML = parameters.toString();

						boolean isOrderInsert = ecommerceService.insertOrder(userOrders, request);
						if (isOrderInsert) {
							model.addAttribute("msg", "Payment successfully done.");
							model.addAttribute("transactionId", userOrders.getTxnid());
							model.addAttribute("orderId", userOrders.getOrderid());
							model.addAttribute("status", CommonEnums.STATUS.ACTIVE.ID);
						} else {
							model.addAttribute("msg","There is some error. Please contact us");
							model.addAttribute("status", CommonEnums.STATUS.INACTIVE.ID);
						}
						
					} else {
						model.addAttribute("msg", "Payment Failed");
						model.addAttribute("status", CommonEnums.STATUS.INACTIVE.ID);
					}
				} else {
					outputHTML = "<b>Checksum mismatched.</b>";
					model.addAttribute("msg", "Payment detailed miss match.");
					model.addAttribute("status", CommonEnums.STATUS.INACTIVE.ID);
				}
				model = productService.setProductservice(model);
				logger.info("output HTMl" + outputHTML);
			} catch (Exception e) {
				outputHTML = e.toString();
			}
			return "frontend/paymentDone";
		} else {
			return "redirect:login";
		}
	}

	@RequestMapping(value = { "/removeFromCart/{deleteFor}/{cartId}" }, method = { RequestMethod.GET })
	public String removeFromCart(HttpServletRequest request, ModelMap model,
			@PathVariable("deleteFor") String deleteFor, @PathVariable("cartId") int cartId) throws Exception {
		// Product and service swap is done from UI
		User user = GenUtilities.getLoggedInUser();

		logger.info("cartId==" + cartId);
		boolean isDelete = ecommerceService.removeFromCart(cartId);
		if (isDelete) {
			UserCart myCart = EcommerceUtils.getCartInSession(request);
			if (deleteFor.equals(BASIC_STRINGS.PRODUCTS.getStringName())) {
				List<Products> productList = myCart.getListOfProducts();
				productList = productList.stream().filter(x -> x.getUserCartProductPK() != Long.valueOf(cartId))
						.collect(Collectors.toList());
				myCart.setListOfProducts(productList);
			} else if (deleteFor.equals(BASIC_STRINGS.SERVICES.getStringName())) {
				List<Services> services = myCart.getListOfService();
				services = services.stream().filter(x -> x.getUserCartServicetPK() != Long.valueOf(cartId))
						.collect(Collectors.toList());
				myCart.setListOfService(services);
			}
			request.getSession().setAttribute("myCart", myCart);
			return "redirect:/ecommerce/getUserCart/" + user.getId();
		}
		return null;
	}
}
