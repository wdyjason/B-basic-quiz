package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createAUser(@RequestBody User newUser) {
        return userService.save(newUser);
    }
}
