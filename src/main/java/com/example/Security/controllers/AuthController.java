package com.example.Security.controllers;

// Code written by Dimitri Liubomudrov

import com.example.Security.exception.EmailAlreadyExistsException;
import com.example.Security.exception.UsernameAlreadyExistsException;
import com.example.Security.models.User;
import com.example.Security.services.UserService;
import com.example.Security.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    private final UserValidator userValidator;


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {


        userValidator.validate(user, bindingResult);

        try {
            userService.register(user);
        } catch (UsernameAlreadyExistsException e) {
            bindingResult.rejectValue("username", "error.user", e.getMessage());
            return "registration";
        } catch (EmailAlreadyExistsException e) {
            bindingResult.rejectValue("email", "error.user", e.getMessage());
            return "registration";
        }

        return "redirect:/auth/login";
    }

}
