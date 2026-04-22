package com.handa.hello.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handa.hello.User;
import com.handa.hello.service.UserService;
import com.handa.springlite.annotation.Autowired;
import com.handa.springlite.annotation.GetMapping;
import com.handa.springlite.annotation.PathVariable;
import com.handa.springlite.annotation.RestController;
import com.handa.springlite.exception.DataAccessException;

@RestController
public class ApiController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @GetMapping("/api/user/{email}")
    Map<String, Boolean> userExist(@PathVariable("email") String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        try {
            userService.getUser(email);
            return Map.of("result", Boolean.TRUE);
        } catch (DataAccessException e) {
            return Map.of("result", Boolean.FALSE);
        }
    }

    @GetMapping("/api/users")
    List<User> users() {
        return userService.getUsers();
    }
}
