package com.digify.dao;

import java.util.List;

import com.digify.model.User;
import com.digify.model.UserBookingDetails;

public interface UserDao {
User findUserByMobile(String mobileno);

void saveUser(User user);

void updateUser(User user);

void deleteUserById(long id);

List<User> findAllUsers();

void deleteAllUsers();

public boolean isUserExist(User user);

User getUserByUserName(String userName, String password);

List<String> getUserRoles(Long userId);

void insertUserRole(long id);

boolean insertPotentialCuastomer(String name, String phone);

User findById(long id);

List<UserBookingDetails> getUserBookingDetails();

void activateUser(long userId);

User getRegistrationTokenAndStatus(long userId);

long insertUser(User user);

User checkUserByEmailorID(String emailorID);

boolean insertPassGenToken(Long userId, String token);

String getpassGenToken(long userId);


}
