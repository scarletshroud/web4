package slayer404.web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import slayer404.web4.model.User;
import slayer404.web4.repository.UserRepository;
import slayer404.web4.utils.HashEncoder;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
    private final UserRepository userRepo;

    @Autowired
    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody User user) {
        Map<String, Object> answer = new HashMap<>();
        if (userRepo.findByUsername(user.getUsername()) != null) {
            answer.put("success", false);
            answer.put("token", "User already exists");
            return new ResponseEntity<>(answer, HttpStatus.CONFLICT);
        }

        user.setPassword(HashEncoder.sha256(user.getPassword()));
        userRepo.save(user);

        answer.put("success", true);
        answer.put("token", "Registration successful");
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }
}
