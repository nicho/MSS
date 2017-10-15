package com.aus.user.service;

import java.util.List;

import com.aus.user.model.User;

public interface UserService {

	User findUserByLoginName(String loginName);

	List<User> getAllUser();

	String registerUser(User user);

	User findUserById(String id);

	String updateUserByid(User user);
}
