package slayer404.web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import slayer404.web4.exceptions.UserExistsException;
import slayer404.web4.model.User;
import slayer404.web4.repository.UserRepository;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Map<String, Object> model) throws UserExistsException {

        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userRepo.save(user);

        return "redirect:/auth/login";
    }

}
