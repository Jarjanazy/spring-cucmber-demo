package jalil.springcucumberdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepo catRepo;

    public Optional<Cat> findByName(String name) {
        return catRepo.findByName(name);
    }

    public Cat addCat(String name) {
        return catRepo.save(new Cat(name));
    }

}
