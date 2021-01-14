package slayer404.web4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "value_x")
    private double valueX;

    @Column(name = "value_y")
    private double valueY;

    @Column(name = "value_r")
    private int valueR;

    @Column(name = "answer")
    private boolean answer;

    @Column(name = "creation_time")
    private String time;

    @Column(name = "work_time")
    private long workTime;

    public Result() { }

    public Result(double valueX, double valueY, int valueR,
                  boolean answer, String time, long workTime, long userId ) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.valueR = valueR;
        this.answer = answer;
        this.time = time;
        this.workTime = workTime;
        this.userId = userId;
    }
}
