package slayer404.web4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slayer404.web4.model.Result;
import slayer404.web4.model.User;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> getAllByUserId(long id);
    List<Result> removeAllByUserId(long id);
}
