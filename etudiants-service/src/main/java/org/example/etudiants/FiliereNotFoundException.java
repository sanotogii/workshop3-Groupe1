package org.example.etudiants;

public class FiliereNotFoundException extends RuntimeException {

    public FiliereNotFoundException(Long filiereId) {
        super("Filiere introuvable avec l'id " + filiereId);
    }
}
