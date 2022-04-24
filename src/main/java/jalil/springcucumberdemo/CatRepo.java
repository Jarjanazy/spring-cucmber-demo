package jalil.springcucumberdemo;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CatRepo extends CrudRepository<Cat, Long> {
    Optional<Cat> findByName(String name);
}
