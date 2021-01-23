package slayer404.web4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
}

