package com.digify.service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.Enums.CommonEnums.STATUS;
import com.digify.dao.UserDao;
import com.digify.exception.GenericException;
import com.digify.model.User;
import com.digify.model.UserBookingDetails;
import com.digify.utils.AESEncrypter;
import com.digify.utils.GenUtilities;
import com.digify.utils.LoginUtils;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Autowired LoginUtils loginutils;
	
	private @Autowired VelocityEngine velocityEngine;
	
	@Autowired MailingService mailingService;

	@Value("${mail.username}")
	private String senderMailId;

	@Value("${mail.admin}")
	private String adminMailId;

	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public User findUserByMobile(String mobileno) {
		return userDao.findUserByMobile(mobileno);
	}

	@Override
	@Transactional
	public User saveUser(String mobileNo, String otp) throws Exception {

		User usr = new User();
		try {
			usr.setMobileNo(mobileNo);
			if (!otp.isEmpty()) {
				usr.setOtp(Long.valueOf(otp));
			}
			usr.setName(BASIC_STRINGS.DEFAULT_USER.getStringName());
			usr.setStatus(STATUS.ACTIVE.ID);
			userDao.saveUser(usr);

			/**
			 * This part of vode with insert the user role in DB
			 */
			userDao.insertUserRole(usr.getId());
		} catch (DataAccessException e) {
			logger.error("DataAccessException and roleback =" + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception ====" + e.getMessage());
		}
		return usr;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByUserName(String userName, String password) {
		User user = userDao.getUserByUserName(userName, password);
		/*
		 * if (user != null && user.getId() > 0) { UserAuth userAuth = new UserAuth();
		 * userAuth.setUserName(userName); userAuth.setUserId(user.getId());
		 * userAuth.setDeviceId(deviceId);
		 * userAuth.setExpiryInMills(ApplicationConstants.EXPIRY_TIME_IN_MILLI_SEC);
		 * userAuth.setAuthToken(user.getToken());
		 * userAuthService.createUserAuth(userAuth); }
		 */
		return user;
	}

	@Override
	public List<String> getUserRoles(Long userId) {

		List<String> userRole = userDao.getUserRoles(userId);
		return userRole;
	}

	@Override
	public boolean insertPotentialCuastomer(String name, String phone) {
		boolean bool = userDao.insertPotentialCuastomer(name, phone);
		if (bool) {
			ResponseEntity<String> response = loginutils.sendSms(name, phone);
		}
		return bool;
	}

	@Override
	public List<UserBookingDetails> getUserBookingDetails() {
		return userDao.getUserBookingDetails();
	}

	@Override
	public String activateUser(String token) throws Exception {
		token = URLDecoder.decode(token, "UTF-8");

		// String plainText=GenUtilitis.decrypt(token);

		String plainText = AESEncrypter.decrypt(token);

		long userId = Long.parseLong(plainText.split("##")[1]);

		User user = userDao.getRegistrationTokenAndStatus(userId);

		String message = null;

		if (user == null || user.getToken() == null || !plainText.equalsIgnoreCase(user.getToken())) {
			message = "Invalid token";
		} else if (user.getStatus() == 1) {
			message = "You are already activated please login";
		} else {
			userDao.activateUser(userId);
			message = "You are activated please login";
		}
		return message;
	}

	@Transactional(rollbackFor = Throwable.class)
	public long insertUser(User user) throws GenericException {
		logger.debug("::userRegistration()");
		User checkUser = checkUserByEmailorID(user.getEmail());
		if (checkUser != null) {
			GenericException exception = new GenericException();
			exception.setMessage("Email already registered!!");
			throw exception;
		}
		long getuserUid = userDao.insertUser(user);
		userDao.insertUserRole(getuserUid);

		return getuserUid;
	}

	@Override
	public User checkUserByEmailorID(String emailorID) {
		return userDao.checkUserByEmailorID(emailorID);
	}

	@Override
	public boolean insertPassGenToken(Long userId, String token) {
		return userDao.insertPassGenToken(userId, token);
	}

	@Override
	public boolean contactUsSubmit(String name, String email, String subject, String message, String dateOfContact, String mobileNo) {
		boolean bool = userDao.contactUsSubmit(name, email, subject, message , dateOfContact , mobileNo);
		if (bool) {
			try {
				/*
				 * if(GenUtilities.isValidEmail(email)) { Map<String, Object> storemap = new
				 * HashMap<String, Object>(); storemap.put("name",
				 * requestQuotes.getPersonName()); storemap.put("email",
				 * requestQuotes.getPersonEmail()); storemap.put("mobile",
				 * requestQuotes.getMobile()); storemap.put("quoteDetails",
				 * requestQuotes.getQuoteDetails()); storemap.put("DigifyTeam", "Digify Team");
				 * String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				 * "email_Templates/sendEnquiryUser.vm", "UTF-8", storemap);
				 * mailingService.sendMail(senderMailId, new String[] { email }, null,
				 * "Request A Quote", text); }
				 */
				if (GenUtilities.isValidEmail(adminMailId)) {
					Map<String, Object> storemap = new HashMap<String, Object>();
					storemap.put("name", name);
					storemap.put("email", email);
					storemap.put("mobile", mobileNo);
					storemap.put("date", dateOfContact);
					storemap.put("subject", subject);
					storemap.put("message", message);
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
							"email_Templates/sendEnquiryAdmin.vm", "UTF-8", storemap);
					mailingService.sendMail(senderMailId, new String[] { adminMailId }, null, subject, text);
				}
				return true;
			} catch (MailException e) {
				e.printStackTrace();
				return false;
			}
		}

		return bool;
	}

	@Override
	public String getpassGenToken(long userId) {
		return userDao.getpassGenToken(userId);
	}

	@Override
	public boolean resetPassword(User user, String newpassword) {
		return userDao.resetPassword(user,newpassword);
	}

}
