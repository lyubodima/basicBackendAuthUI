package com.example.Security.util;

// Code written by Dimitri Liubomudrov

import com.example.Security.models.User;
import com.example.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        // BAD CODE !!!!!!! - do not use exception !!!!!
        // rewrite to use Optional - check Optional if there is a person - return an error
        // else - do not return an error

        try {
            userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; // OK, user with this username is not found
        }

        errors.rejectValue("username", "", "Username already exists");

    }

    //? generate method for Object ???
}
