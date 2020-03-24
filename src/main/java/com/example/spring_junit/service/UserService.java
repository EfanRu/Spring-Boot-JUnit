package com.example.spring_junit.service;

import com.example.spring_junit.model.Role;
import com.example.spring_junit.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    boolean addUser(User u);
    boolean addRole(Role r);
    boolean deleteUser(String id);
    boolean updateUser(String id, String firstName, String lastName, String phoneNumber, String role, String login, String password);
    boolean updateUser(User user);
    boolean checkAuth(String login, String password);
    User getUserById(String id);
    User getUserByLogin(String login);
    Collection<Role> getRoleByName(Collection<String> name);
}
