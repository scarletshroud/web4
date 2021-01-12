package slayer404.web4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slayer404.web4.model.Result;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> getAllByUsername();

}
