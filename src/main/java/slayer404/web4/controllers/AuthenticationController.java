package slayer404.web4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping ("/success")
    public String getSuccessPage() {
        return "index";
    }
}
