package slayer404.web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import slayer404.web4.model.User;
import slayer404.web4.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {
    private final UserRepository userRepo;

    @Autowired
    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(User user) {
        Map<String, Object> answer = new HashMap<>();
        if (userRepo.findByUsername(user.getUsername()) != null) {
            answer.put("success", false);
            answer.put("message", "User already exists");
            return new ResponseEntity<>(answer, HttpStatus.CONFLICT);
        }

        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userRepo.save(user);

        answer.put("success", true);
        answer.put("message", "Registration successful");
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }
}
