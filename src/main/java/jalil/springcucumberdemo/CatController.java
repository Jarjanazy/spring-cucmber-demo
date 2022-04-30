package jalil.springcucumberdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("cat")
    public ResponseEntity<Cat> addCat(@RequestBody NewCatDto catDto) {
        Cat cat = catService.addCat(catDto.getName());

        return ResponseEntity.ok().body(cat);
    }
}
