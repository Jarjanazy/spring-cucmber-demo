package jalil.springcucumberdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;
    @GetMapping("/cat/{name}")
    public ResponseEntity<Cat> getCatByName(@PathVariable String name) {
        Optional<Cat> cat = catService.findByName(name);
        return cat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
