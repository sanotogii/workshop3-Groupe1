package org.example.etudiants;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping
    public List<EtudiantResponse> findAll() {
        return etudiantService.findAll();
    }

    @GetMapping("/{id}")
    public EtudiantResponse findById(@PathVariable("id") Long id) {
        return etudiantService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Etudiant> create(@Valid @RequestBody Etudiant etudiant) {
        Etudiant created = etudiantService.create(etudiant);
        return ResponseEntity.created(URI.create("/api/etudiants/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Etudiant update(@PathVariable("id") Long id, @Valid @RequestBody Etudiant etudiant) {
        return etudiantService.update(id, etudiant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        etudiantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EtudiantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEtudiantNotFound(EtudiantNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(FiliereNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFiliereNotFound(FiliereNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(FiliereUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String handleFiliereUnavailable(FiliereUnavailableException exception) {
        return exception.getMessage();
    }
}
