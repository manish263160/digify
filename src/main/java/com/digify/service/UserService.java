package com.digify.service;

import java.util.List;

import com.digify.exception.GenericException;
import com.digify.model.User;
import com.digify.model.UserBookingDetails;

public interface UserService {
	
	User findById(long id);
	
	User findUserByMobile(String mobileNo);
	
	User saveUser(String mobileNo , String otp) throws Exception;
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	String activateUser(String token) throws Exception;
	
	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);

	User getUserByUserName(String userName, String password);

	List<String> getUserRoles(Long userId);

	boolean insertPotentialCuastomer(String name, String phone);

	List<UserBookingDetails> getUserBookingDetails();

	long insertUser(User arg0) throws GenericException;

	User checkUserByEmailorID(String emailorID);

	boolean insertPassGenToken(Long userId, String token);

	boolean contactUsSubmit(String name, String email, String subject, String message);

	String getpassGenToken(long parseLong);

	boolean resetPassword(User user, String newpassword);


	
}
