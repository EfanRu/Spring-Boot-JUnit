package com.example.spring_junit.dao;

import com.example.spring_junit.model.Role;
import com.example.spring_junit.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    boolean addUser(User user);
    boolean addRole(Role rrole);
    boolean deleteUser(String id);
    boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password);
    boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login);
    boolean updateUser(User user);
    boolean checkAuth(String login, String password);
    User getUserByLogin(String login);
    User getUserById(String id);
    Role getRoleByName(String name);
}
