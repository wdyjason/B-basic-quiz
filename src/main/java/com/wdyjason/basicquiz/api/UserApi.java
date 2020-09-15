package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;
}
