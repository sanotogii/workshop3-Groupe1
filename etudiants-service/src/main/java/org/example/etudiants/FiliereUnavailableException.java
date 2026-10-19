package org.example.etudiants;

public class FiliereUnavailableException extends RuntimeException {

    public FiliereUnavailableException(Long filiereId) {
        super("Impossible de recuperer la filiere avec l'id " + filiereId);
    }

    public FiliereUnavailableException(Long filiereId, Throwable cause) {
        super("Service filieres indisponible. Impossible de recuperer la filiere avec l'id " + filiereId, cause);
    }
}
