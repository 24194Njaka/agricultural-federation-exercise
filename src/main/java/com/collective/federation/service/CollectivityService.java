package com.collective.federation.service;

import com.collective.federation.entity.CollectivityEntity;
import com.collective.federation.repository.CollectivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;

    public CollectivityService(CollectivityRepository collectivityRepository) {
        this.collectivityRepository = collectivityRepository;
    }


    @Transactional
    public void createCollectivity(CollectivityEntity collectivity) {
        if (collectivityRepository.existsById(collectivity.getId())) {
            throw new RuntimeException("Error: Collectivity with ID " + collectivity.getId() + " already exists.");
        }

        collectivityRepository.save(collectivity);

        System.out.println("Collectivity '" + collectivity.getName() + "' has been successfully created.");
    }
}