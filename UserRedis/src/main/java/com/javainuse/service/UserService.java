package com.javainuse.service;

import java.util.List;

import com.javainuse.entity.User;

public interface UserService {
     List<User> getAllUsers();
     User getUser(int id);
     void addUser(User user);
     void updateUser(User user);
     void deleteUser(int item);
}
