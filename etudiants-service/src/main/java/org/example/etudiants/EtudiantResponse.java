package org.example.etudiants;

public record EtudiantResponse(Long id, String nom, String prenom, String email, Long filiereId, FiliereDto filiere) {

    public static EtudiantResponse from(Etudiant etudiant, FiliereDto filiere) {
        return new EtudiantResponse(
                etudiant.getId(),
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getEmail(),
                etudiant.getFiliereId(),
                filiere
        );
    }
}
