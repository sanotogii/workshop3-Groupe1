package org.example.filieres;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    private final FiliereService filiereService;

    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @GetMapping
    public List<Filiere> findAll() {
        return filiereService.findAll();
    }

    @GetMapping("/{id}")
    public Filiere findById(@PathVariable("id") Long id) {
        return filiereService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Filiere> create(@Valid @RequestBody Filiere filiere) {
        Filiere created = filiereService.create(filiere);
        return ResponseEntity.created(URI.create("/api/filieres/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Filiere update(@PathVariable("id") Long id, @Valid @RequestBody Filiere filiere) {
        return filiereService.update(id, filiere);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        filiereService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(FiliereNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(FiliereNotFoundException exception) {
        return exception.getMessage();
    }
}
