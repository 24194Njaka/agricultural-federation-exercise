package com.collective.federation.service;

import com.collective.federation.entity.CollectivityEntity;
import com.collective.federation.repository.CollectivityRepository;
import org.springframework.stereotype.Service;

@Service
public class CollectivityService {
    private final CollectivityRepository collectivityRepository;

    public CollectivityService(CollectivityRepository collectivityRepository) {
        this.collectivityRepository = collectivityRepository;
    }

    public void createCollectivity(CollectivityEntity collectivity) {
        // Règle A : Une collectivité doit avoir l'approbation de la fédération
        if (!collectivity.isFederationApproval()) {
            throw new RuntimeException("Creation refused: Federation approval is required.");
        }
        collectivityRepository.save(collectivity);
    }
}