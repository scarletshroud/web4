package slayer404.web4.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Result() { }

    public Result(double valueX, double valueY, int valueR,
                  String answer, String time, long workTime ) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.valueR = valueR;
        this.answer = answer;
        this.time = time;
        this.workTime = workTime;
    }

    @Column(name = "valueX")
    private double valueX;

    @Column(name = "valueY")
    private double valueY;

    @Column(name = "valueR")
    private int valueR;

    @Column(name = "answer")
    private String answer;

    @Column(name = "time")
    private String time;

    @Column(name = "workTime")
    private long workTime;
}
