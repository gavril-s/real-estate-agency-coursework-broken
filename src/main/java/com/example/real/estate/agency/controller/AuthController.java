package com.example.real.estate.agency.controller;

import com.example.real.estate.agency.entity.UserDTO;
import com.example.real.estate.agency.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") UserDTO newUser, HttpServletRequest request) throws ServletException {
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            return "redirect:/register?error";
        }
        if (userService.addUser(newUser)) {
            request.login(newUser.getName(), newUser.getPassword());
            return "redirect:/objects";
        }

        return "redirect:/register?exists";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
