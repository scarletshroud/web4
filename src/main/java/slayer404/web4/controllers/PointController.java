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
import slayer404.web4.validators.ValidatorR;
import slayer404.web4.validators.ValidatorX;
import slayer404.web4.validators.ValidatorY;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class PointController {
    private final ResultRepository resultRepo;

    @Autowired
    public PointController(ResultRepository resultRepo) {
        this.resultRepo = resultRepo;
    }

    @PostMapping("/handler")
    private ResponseEntity<?> handleRequest(@RequestBody Point point) {
        long startTime = System.currentTimeMillis();
        StringBuilder errorMessage = new StringBuilder();

        if (!isRequestDataValid(point, errorMessage)) {
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
                point.getUserId()
        );

        resultRepo.save(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/resource")
    private ResponseEntity<?> getResults() {
        List<Result> results = resultRepo.getAllByUserId(1);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    private boolean isRequestDataValid(Point point, StringBuilder errorMessage) {
        boolean err = false;

        try {
            new ValidatorX().validate(point.getValueX());
        } catch (ValidationException ex) {
            errorMessage.append(ex.getMessage());
            err = true;
        }

        try {
            new ValidatorY().validate(point.getValueY());
        } catch (ValidationException ex) {
            errorMessage.append(ex.getMessage());
            err = true;
        }

        try {
            new ValidatorR().validate(point.getValueR());
        } catch (ValidationException ex) {
            errorMessage.append(ex.getMessage());
            err = true;
        }

        return err;
    }
}
