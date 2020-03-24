package com.example.spring_junit.security;

import com.example.spring_junit.model.Role;
import com.example.spring_junit.model.User;
import com.example.spring_junit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@PropertySource("classpath:application.properties")
@Order(3)
public class DefaultUserAndAdmin {
    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;
    
    @Bean
    public void DefaultUserAndAdmin() {
        Collection<Role> adminRole = new ArrayList<>();
        Collection<Role> userRole = new ArrayList<>();
        userRole.add(new Role("user"));
        adminRole.add(new Role("admin"));

        User user = new User();
        user.setFirstName("Default user");
        user.setLastName("Default user");
        user.setPhoneNumber(99L);
        user.setLogin(env.getProperty("db.default.user.login"));
        user.setPassword(env.getProperty("db.default.user.password"));
        user.setRole(userRole);
        userService.addUser(user);

        User admin = new User();
        admin.setFirstName("Default admin");
        admin.setLastName("Default admin");
        admin.setPhoneNumber(99L);
        admin.setLogin(env.getProperty("db.default.admin.login"));
        admin.setPassword(env.getProperty("db.default.admin.password"));
        admin.setRole(adminRole);
        userService.addUser(admin);
    }
}
