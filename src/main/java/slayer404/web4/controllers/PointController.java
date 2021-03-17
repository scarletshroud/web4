package slayer404.web4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import slayer404.web4.exceptions.ValidationException;
import slayer404.web4.model.Point;
import slayer404.web4.model.Result;
import slayer404.web4.repository.ResultRepository;
import slayer404.web4.utils.DataFormatter;
import slayer404.web4.utils.HitChecker;
import slayer404.web4.validators.ValidatorPicValues;
import slayer404.web4.validators.ValidatorInt;
import slayer404.web4.validators.ValidatorY;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PointController {
    private final ResultRepository resultRepo;

    @Autowired
    public PointController(ResultRepository resultRepo) {
        this.resultRepo = resultRepo;
    }

    @PostMapping("/handler")
    private ResponseEntity<?> handleRequest(HttpSession session, @RequestBody Point point) {

        if (SessionService.global.contains(session.getId())) {
            long startTime = System.currentTimeMillis();
            StringBuilder errorMessage = new StringBuilder();

            if (!isRequestDataValid(point, errorMessage, point.isFromPic())) {
                return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
            }

            boolean answer =
                    HitChecker.isInArea(
                            point.getValueX(), point.getValueY(), point.getValueR()
                    );

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            Result result = new Result(
                    Double.parseDouble(DataFormatter.formatValue(point.getValueX())),
                    Double.parseDouble(DataFormatter.formatValue(point.getValueY())),
                    Integer.parseInt(point.getValueR()),
                    answer,
                    formatter.format(LocalDateTime.now()),
                    System.currentTimeMillis() - startTime,
                    SessionService.global.get(session.getId())
            );

            resultRepo.save(result);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not logged in!", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/resource")
    private ResponseEntity<?> getResults(HttpSession session) {
        if (SessionService.global.contains(session.getId())) {
            Long userId = SessionService.global.get(session.getId());
            List<Result> results = resultRepo.getAllByUserId(userId);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not logged in!", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/clear")
    private ResponseEntity<?> clear(HttpSession session) {
        if (SessionService.global.contains(session.getId())) {
            resultRepo.removeAllByUserId(SessionService.global.get(session.getId()));
            return new ResponseEntity<>("Successfully cleared.", HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not logged in!", HttpStatus.UNAUTHORIZED);
    }

    private boolean isRequestDataValid(Point point, StringBuilder errorMessage, boolean isFromPic) {
        boolean err = true;

        try {
            if (isFromPic) {
                new ValidatorPicValues().validate(point.getValueX(), "X");
            }
            else {
                new ValidatorInt().validate(point.getValueX(), "X");
            }
        } catch (ValidationException ex) {
            errorMessage.append(ex.getMessage());
            err = false;
        }

        try {
            if (isFromPic) {
                new ValidatorPicValues().validate(point.getValueY(), "Y");
            } else {
                new ValidatorY().validate(point.getValueY(), "Y");
            }
        } catch (ValidationException ex) {
            errorMessage.append(ex.getMessage());
            err = false;
        }

        try {
            new ValidatorInt().validate(point.getValueR(), "R");
        } catch (ValidationException ex) {
            errorMessage.append(ex.getMessage());
            err = false;
        }

        return err;
    }
}
