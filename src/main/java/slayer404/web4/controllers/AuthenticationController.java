package slayer404.web4.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @GetMapping ("/login")
    public ResponseEntity<?> getLoginStatus() {
        Map<String, Object> answer = new HashMap<>();
        answer.put("success", true);
        answer.put("message", "Authorization successful");
        return new ResponseEntity<>(answer, HttpStatus.ACCEPTED);
    }
}
