package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
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
        return userService.saveUser(newUser);
    }

    @GetMapping("/{id}")
    public User getAUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findOne(id);
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createAnEducation(@RequestBody Education education) {
        return userService.saveEducation(education);
    }
}
