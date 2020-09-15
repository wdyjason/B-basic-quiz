package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createAUser(@RequestBody @Valid User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/{id}")
    public User getAUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findOneUser(id);
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createAnEducation(@RequestBody @Valid Education education,
                                        @PathVariable(name = "id") Long userId) throws UserNotFoundException {
        return userService.saveEducation(userId, education);
    }

    @GetMapping("/{id}/educations")
    public List<Education> getUserEducation(@PathVariable(name = "id") Long userId) {
        return userService.findUserEducations(userId);
    }
}
