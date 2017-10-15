package com.aus.user.dao;

import java.util.List;

import com.aus.user.model.User;

public interface UserMapper {
 
    User findUserByLoginName(String loginName);
    
    List<User> getAllUser();
    
    int registerUser(User user);
    
    User findUserById(String id);
    
    int updateUserByid(User user);
    
}