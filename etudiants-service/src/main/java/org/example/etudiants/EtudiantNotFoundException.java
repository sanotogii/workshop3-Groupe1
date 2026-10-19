package org.example.etudiants;

public class EtudiantNotFoundException extends RuntimeException {

    public EtudiantNotFoundException(Long id) {
        super("Etudiant introuvable avec l'id " + id);
    }
}
