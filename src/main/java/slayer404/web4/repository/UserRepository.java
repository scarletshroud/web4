package slayer404.web4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slayer404.web4.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
