package org.example.etudiants;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final FiliereClient filiereClient;

    public EtudiantService(EtudiantRepository etudiantRepository, FiliereClient filiereClient) {
        this.etudiantRepository = etudiantRepository;
        this.filiereClient = filiereClient;
    }

    public List<EtudiantResponse> findAll() {
        return etudiantRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public EtudiantResponse findById(Long id) {
        return toResponse(findEntityById(id));
    }

    public Etudiant create(Etudiant etudiant) {
        filiereClient.findById(etudiant.getFiliereId());
        etudiant.setId(null);
        return etudiantRepository.save(etudiant);
    }

    public Etudiant update(Long id, Etudiant etudiant) {
        filiereClient.findById(etudiant.getFiliereId());
        Etudiant existing = findEntityById(id);
        existing.setNom(etudiant.getNom());
        existing.setPrenom(etudiant.getPrenom());
        existing.setEmail(etudiant.getEmail());
        existing.setFiliereId(etudiant.getFiliereId());
        return etudiantRepository.save(existing);
    }

    public void delete(Long id) {
        Etudiant existing = findEntityById(id);
        etudiantRepository.delete(existing);
    }

    private Etudiant findEntityById(Long id) {
        return etudiantRepository.findById(id).orElseThrow(() -> new EtudiantNotFoundException(id));
    }

    private EtudiantResponse toResponse(Etudiant etudiant) {
        FiliereDto filiere = filiereClient.findById(etudiant.getFiliereId());
        return EtudiantResponse.from(etudiant, filiere);
    }
}
