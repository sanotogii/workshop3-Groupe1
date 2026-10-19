package org.example.etudiants;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;

    public DataInitializer(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public void run(String... args) {
        if (etudiantRepository.count() > 0) {
            return;
        }
        etudiantRepository.save(create("Alaoui", "Sara", "sara.alaoui@example.com", 1L));
        etudiantRepository.save(create("Benali", "Youssef", "youssef.benali@example.com", 2L));
        etudiantRepository.save(create("El Amrani", "Nora", "nora.elamrani@example.com", 1L));
    }

    private Etudiant create(String nom, String prenom, String email, Long filiereId) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setFiliereId(filiereId);
        return etudiant;
    }
}
