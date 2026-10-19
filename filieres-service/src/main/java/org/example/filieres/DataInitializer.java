package org.example.filieres;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FiliereRepository filiereRepository;

    public DataInitializer(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public void run(String... args) {
        if (filiereRepository.count() > 0) {
            return;
        }
        filiereRepository.save(create("GI", "Genie Informatique", "Developpement logiciel et systemes d'information"));
        filiereRepository.save(create("GE", "Genie Electrique", "Electronique, energie et automatisme"));
        filiereRepository.save(create("GIND", "Genie Industriel", "Production, logistique et qualite"));
    }

    private Filiere create(String code, String libelle, String description) {
        Filiere filiere = new Filiere();
        filiere.setCode(code);
        filiere.setLibelle(libelle);
        filiere.setDescription(description);
        return filiere;
    }
}
