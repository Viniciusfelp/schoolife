package com.aps.schoolife.controllers;


import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@ModelAttribute("username") String username) {
        return "home";
    }

    @ModelAttribute("username")
    public String getUsername(Principal principal) {
        return (principal != null) ? principal.getName() : "Anonymous";
    }
}