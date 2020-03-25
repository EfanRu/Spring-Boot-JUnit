package com.example.spring_junit.controller;

import com.example.spring_junit.model.Role;
import com.example.spring_junit.model.User;
import com.example.spring_junit.model.UserDto;
import com.example.spring_junit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class AdminRestController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminRestController.class);

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto, Errors errors) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(userDto.getRole()));
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                roles
        );
        if (userService.addUser(user)) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.unprocessableEntity().body(user);
        }
    }

    @GetMapping("/admin/all")
    public List<User> allUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/{id}")
    public User getUser(@Valid @ModelAttribute("id") String id, Errors errors) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public User editUserPage(@ModelAttribute("id") String id, ModelMap model) {
        return userService.getUserById(id);
    }

    @PutMapping(value = "/admin/{id}")
    public User editUser(@RequestBody UserDto userDto) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(userDto.getRole()));
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                roles
        );
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/admin/{id}")
    public boolean delUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
