package slayer404.web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import slayer404.web4.model.User;
import slayer404.web4.repository.UserRepository;
import slayer404.web4.utils.HashEncoder;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final UserRepository userRepo;

    @Autowired
    public AuthenticationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        User dbUser = userRepo.findByUsername(user.getUsername());
        if ((dbUser != null)) {
            String password = HashEncoder.sha256(user.getPassword());
            return dbUser.getPassword().equals(password);
        }
        return false;
    }
}
