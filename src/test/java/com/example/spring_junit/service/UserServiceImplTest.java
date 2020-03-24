package com.example.spring_junit.service;

import com.example.spring_junit.SpringJunitApplication;
import com.example.spring_junit.model.Role;
import com.example.spring_junit.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = SpringJunitApplication.class)
@SpringBootTest
class UserServiceImplTest extends Assert {
    private static User testUser;
    @Autowired
    private UserService userService;
////
////    @Test
////    void addUser() {
////        testUser = new User(
////                "testUser FirstName",
////                "testUser LastName",
////                "testUser Login",
////                "testUser Password",
////                999L,
////                new Role("admin"));
////        userService.addUser(testUser);
////        assertEquals("testUser Login", userService.getUserByLogin("testUser Login").getLogin());
////    }
//
//    @Test
//    void loadUserByUsername() {
//        User userExp = (User) userService.loadUserByUsername(testUser.getLogin());
//        testUser.setId(3L);
//        User userAct = testUser;
//        assertEquals(userExp, userAct);
//    }
////
////    @Test
////    void getAllUsers() {
////        List<User> usersExp = new ArrayList<>();
////        usersExp.add(new User(
////                1L,
////                "Default user",
////                "Default user",
////                "user",
////                "user",
////                99L,
////                userService.getRoleByName("user")
////        ));
//        usersExp.add(new User(
//                2L,
//                "Default admin",
//                "Default admin",
//                "admin",
//                "admin",
//                99L,
//                userService.getRoleByName("admin")
//        ));
//        usersExp.add(testUser);
//        List<User> usersAct = userService.getAllUsers();
//        usersAct.get(0).setPassword("user");
//        usersAct.get(1).setPassword("admin");
//        assertEquals(usersExp, usersAct);
//    }
//
//    @Test
//    void deleteUser() {
//        User userFromDB = userService.getUserByLogin(testUser.getLogin());
//        userService.deleteUser(userFromDB.getId().toString());
//        User nullUserExp = new User();
//        User nullUserAct = userService.getUserById(userFromDB.getId().toString());
//        assertEquals(nullUserAct, nullUserExp);
//    }
}