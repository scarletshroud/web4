package slayer404.web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import slayer404.web4.model.User;
import slayer404.web4.repository.UserRepository;

import java.util.Map;

@RestController
public class RegistrationController {
    private final UserRepository userRepo;

    @Autowired
    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "index";
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(User user) {

        if (userRepo.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("User exists!", HttpStatus.CONFLICT);
        }

        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userRepo.save(user);

        return new ResponseEntity<>("Registration completed successfully!", HttpStatus.CREATED);
    }

}
