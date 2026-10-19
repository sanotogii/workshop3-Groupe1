package org.example.filieres;

public class FiliereNotFoundException extends RuntimeException {

    public FiliereNotFoundException(Long id) {
        super("Filiere introuvable avec l'id " + id);
    }
}
