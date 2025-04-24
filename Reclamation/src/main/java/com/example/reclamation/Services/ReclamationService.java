package com.example.reclamation.Services;

import com.example.reclamation.Repositories.ReclamationRepository;
import com.example.reclamation.entities.Reclamation;
import com.example.reclamation.entities.UserCheckRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReclamationService implements IReclamationService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final ReclamationRepository reclamationRepository;

    @Autowired
    public ReclamationService(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }


    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        // 1. Create a small UserCheckRequest object
        Map<String, Object> request = new HashMap<>();
        request.put("userId", reclamation.getUserId());

        Object response = rabbitTemplate.convertSendAndReceive("user-check-queue", request);


        if (response != null && Boolean.parseBoolean(response.toString())) {
            // 3. If user exists, save the reclamation
            return reclamationRepository.save(reclamation);
        } else {
            throw new RuntimeException("User does not exist. Reclamation not saved.");
        }
    }

    @Override
    public Reclamation updateReclamation(Long id, Reclamation reclamation) {
        Optional<Reclamation> existing = reclamationRepository.findById(id);
        if (existing.isPresent()) {
            Reclamation r = existing.get();
            r.setLieuDepart(reclamation.getLieuDepart());
            r.setLieuArrivee(reclamation.getLieuArrivee());
            r.setHeureDepart(reclamation.getHeureDepart());
            r.setHeureArrivee(reclamation.getHeureArrivee());
            r.setDescription(reclamation.getDescription());
            r.setStatut(reclamation.getStatut());
            return reclamationRepository.save(r);
        }
        return null;
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public Reclamation getReclamationById(Long id) {
        return reclamationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> getReclamationsByStatut(String statut) {
        return reclamationRepository.findByStatut(statut);
    }
}
