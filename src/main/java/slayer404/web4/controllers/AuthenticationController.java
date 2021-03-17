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
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthenticationController {
    private final UserRepository userRepo;

    @Autowired
    public AuthenticationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @RequestBody User user) {
        Map<String, Object> answer = new HashMap<>();
        User dbUser = userRepo.findByUsername(user.getUsername());
        if ((dbUser != null)) {
            String password = HashEncoder.sha256(user.getPassword());
            if (dbUser.getPassword().equals(password)) {
                SessionService.global.add(session.getId(), dbUser.getId());
                answer.put("success", true);
                answer.put("token", "Authorization completed successfully!");
                return new ResponseEntity<>(answer, HttpStatus.OK);
            }
        }
        answer.put("success", false);
        answer.put("token", "User doesn't exist!");
        return new ResponseEntity<>(answer, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        SessionService.global.remove(session.getId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
