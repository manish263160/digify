package com.digify.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.controller.AdminController;
import com.digify.dao.AdminDao;
import com.digify.dao.EcommerceDao;
import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.User;
import com.digify.model.UserOrderDetails;
import com.digify.model.UserOrderResponseDto;
import com.digify.model.UserOrders;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.GenUtilities;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    AdminDao adminDao;
    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    EcommerceDao ecommerceDao;
    @Autowired
    ProductService productService;
    
    @Override
    public List<HomepageContentMaster> getHomePageContentMaster() {
        return adminDao.getHomePageContentMaster();
    }

    @Override
    public List<HomepageContent> getAllHomeComponentList(Long homePageComponentMasterId, String viewsFolder) {
        User user = GenUtilities.getLoggedInUser();
        Long userId = user != null ? user.getId() : null;
        List<HomepageContent> list = adminDao.getAllHomeComponentList(userId, homePageComponentMasterId);
        for (HomepageContent homepageContent : list) {
            String ImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
                    + this.applicationProperties.getProperty("homePageContent") + homepageContent.getViewFolder() + "/"
                    + homepageContent.getImageName();
            homepageContent.setImageUrl(ImgUrl);
        }
        return list;
    }

    @Override
    public String insertUpdateHomeComponent(MultipartFile file, Object content, String tableName, String actionName,
                                            MultipartFile iconImg) {
        User user = GenUtilities.getLoggedInUser();
        String imagePath = this.applicationProperties.getProperty("imageFolder");
        if (tableName.equals("homepage_content")) {
            HomepageContent homeComponent = (HomepageContent) content;
            imagePath = imagePath + BASIC_STRINGS.ADMIN.getStringName()
                    + this.applicationProperties.getProperty("homePageContent") + homeComponent.getViewFolder() + "/";
        } else if (tableName.equals("products")) {
            imagePath = imagePath + BASIC_STRINGS.ADMIN.getStringName()
                    + this.applicationProperties.getProperty("products");
        } else if (tableName.equals("services")) {
            imagePath = imagePath + BASIC_STRINGS.ADMIN.getStringName()
                    + this.applicationProperties.getProperty("services");
        }
        try {
            String fileName = "";
            String iconImgFileName = "";
            if (file != null && !file.isEmpty()) {
                String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
                        file.getOriginalFilename().length());
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
                Date date = new Date();
                fileName = formatter.format(date) + "_" + file.getOriginalFilename();

                File newFile = GenUtilities.uploadFile(imagePath, fileName, file);
                if (newFile != null) {
                    fileExtension = fileExtension.replaceFirst("\\.", "");
//					GenUtilities.resizeImage(newFile, fileExtension, 206, 206);
                    BufferedImage originalImage = ImageIO.read(newFile);
                    /*
                     * BufferedImage profileMain = GenUtilities.getScaledInstance(originalImage,
                     * 206, 206, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
                     */
                    boolean isUploaded = ImageIO.write(originalImage, fileExtension, new File(imagePath + fileName));
                }
            } else {
                fileName = setExistingFileName(content, tableName, fileName);

            }
            if (iconImg != null && !iconImg.isEmpty()) {
                iconImgFileName = iconFileUpload(iconImg, imagePath);
            } else {
                if (tableName.equals("products")) {
                    iconImgFileName = ((Products) content).getIconImg();
                } else if (tableName.equals("services")) {
                    iconImgFileName = ((Services) content).getIconImg();
                }
            }
            return actionInDb(content, tableName, actionName, user, fileName, iconImgFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String setExistingFileName(Object content, String tableName, String fileName) {
        if (tableName.equals("homepage_content")) {
            fileName = ((HomepageContent) content).getImageName();
        } else if (tableName.equals("products")) {
            fileName = ((Products) content).getProductImage();
        } else if (tableName.equals("services")) {
            fileName = ((Services) content).getServiceImage();
        }
        return fileName;
    }

    /**
     * @param content
     * @param tableName
     * @param actionName
     * @param user
     * @param fileName
     * @param iconFileName
     */
    private String actionInDb(Object content, String tableName, String actionName, User user, String fileName,
                              String iconFileName) {
        if (actionName.equals("insert")) {
            String status = null;
            if (content instanceof HomepageContent) {
                HomepageContent homeComponent = (HomepageContent) content;
                status = adminDao.insertFile(user, fileName, tableName, homeComponent, iconFileName);

                if ("success".equals(status)) {
                    String filepath = setUserUploadedFilePath(user, fileName, "homePageContent");
                    return filepath;
                }
            } else if (content instanceof Products) {
                Products products = (Products) content;
                status = adminDao.insertFile(user, fileName, tableName, products, iconFileName);
                if ("success".equals(status)) {
                    String filepath = setUserUploadedFilePath(user, fileName, "products");
                    return filepath;
                }
            } else if (content instanceof Services) {
                Services service = (Services) content;
                status = adminDao.insertFile(user, fileName, tableName, service, iconFileName);
                if ("success".equals(status)) {
                    String filepath = setUserUploadedFilePath(user, fileName, "services");
                    return filepath;
                }
            }

        } else if (actionName.equals("update")) {
            String status = null;
            if (content instanceof HomepageContent) {
                HomepageContent homeComponent = (HomepageContent) content;
                status = adminDao.updateFile(user, fileName, tableName, homeComponent, iconFileName);
                if ("success".equals(status)) {
                    String filepath = setUserUploadedFilePath(user, fileName, "homePageContent");
                    return filepath;
                }
            } else if (content instanceof Products) {
                Products products = (Products) content;
                status = adminDao.updateFile(user, fileName, tableName, products, iconFileName);
                if ("success".equals(status)) {
                    String filepath = setUserUploadedFilePath(user, fileName, "products");
                    return filepath;
                }
            } else if (content instanceof Services) {
                Services service = (Services) content;
                status = adminDao.updateFile(user, fileName, tableName, service, iconFileName);
                if ("success".equals(status)) {
                    String filepath = setUserUploadedFilePath(user, fileName, "services");
                    return filepath;
                }
            }
        }
        return null;
    }

    private String setUserUploadedFilePath(User user, String fileName, String fileType) {
        String url = null;

        url = applicationProperties.getProperty("appPath") + user.getId()
                + (fileType.equals("homePageContent") ? applicationProperties.getProperty("homePageContent")
                : fileType.equals("products") ? applicationProperties.getProperty("products")
                : applicationProperties.getProperty("services"))
                + fileName;
        return url;
    }

    @Override
    public HomepageContent getHomeComponentById(Long contentId, String viewsFolder) {
        HomepageContent obj = adminDao.getHomeComponentById(contentId);
        String ImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
                + this.applicationProperties.getProperty("homePageContent") + viewsFolder + "/" + obj.getImageName();
        obj.setImageUrl(ImgUrl);
        return obj;
    }

    @Override
    public boolean deleteContent(Long contentId, String imageName, String tableName, String viewFolder) {
        if (imageName != null && !imageName.isEmpty()) {
            String imagePath = this.applicationProperties.getProperty("imageFolder");
            imageName = imagePath + BASIC_STRINGS.ADMIN.getStringName()
                    + this.applicationProperties.getProperty("homePageContent") + viewFolder + "/" + imageName;
            File oldFile = new File(imageName);
            try {
                GenUtilities.delete(oldFile);
                boolean bool = adminDao.deleteContent(contentId, imageName, tableName);
                return bool;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            boolean bool = adminDao.deleteContent(contentId, imageName, tableName);
            return bool;
        }
        return false;
    }

    public ModelMap addUserInModel(ModelMap model) {
        logger.debug("addUserInModel here:");
        User user = GenUtilities.getLoggedInUser();
        model.addAttribute("user", user);
        return model;
    }

    public ModelMap addListHomePageContent(ModelMap model) {
        logger.debug("addListHomePageContent add here:");
        List<HomepageContentMaster> listHomePgcontMaster = getHomePageContentMaster();
        model.addAttribute("listHomePgcontMaster", listHomePgcontMaster);
        return model;

    }

    private String iconFileUpload(MultipartFile iconImg, String imagePath) throws IOException {
        if (iconImg != null && !iconImg.isEmpty()) {
            String iconFileName = iconImg.getOriginalFilename();
            String iconExtension = iconImg.getOriginalFilename()
                    .substring(iconImg.getOriginalFilename().lastIndexOf("."), iconImg.getOriginalFilename().length());
            File newIconFile = GenUtilities.uploadFile(imagePath, iconFileName, iconImg);
            if (newIconFile != null) {
                iconExtension = iconExtension.replaceFirst("\\.", "");
                BufferedImage originalIconImage = ImageIO.read(newIconFile);
                boolean isIconUploaded = ImageIO.write(originalIconImage, iconExtension,
                        new File(imagePath + iconFileName));
                return iconFileName;
            }
        }
        return null;
    }

	@Override
	public List<UserOrders> getAllOrders() {
		List<UserOrders> allOrders =  ecommerceDao.getUserOrders(true);
		return allOrders;
	}

	@Override
	public List<UserOrderResponseDto> getOrderByOrderId(String orderId) {
		List<UserOrderDetails> listUserOrderDetail =  ecommerceDao.getUserOrdersDetails(orderId);
		List<UserOrderResponseDto> userOrderResponseDto = new LinkedList<>();
		for (UserOrderDetails userOrdersDetails : listUserOrderDetail) {
			UserOrderResponseDto dto = new UserOrderResponseDto();
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
		return userOrderResponseDto;
	}
}
