package org.example.filieres;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;

    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    public Filiere findById(Long id) {
        return filiereRepository.findById(id).orElseThrow(() -> new FiliereNotFoundException(id));
    }

    public Filiere create(Filiere filiere) {
        filiere.setId(null);
        return filiereRepository.save(filiere);
    }

    public Filiere update(Long id, Filiere filiere) {
        Filiere existing = findById(id);
        existing.setCode(filiere.getCode());
        existing.setLibelle(filiere.getLibelle());
        existing.setDescription(filiere.getDescription());
        return filiereRepository.save(existing);
    }

    public void delete(Long id) {
        Filiere existing = findById(id);
        filiereRepository.delete(existing);
    }
}
