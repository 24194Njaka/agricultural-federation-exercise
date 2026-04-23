package com.argiculturre.service;

import com.argiculturre.dto.request.AssignIdentityRequest;
import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.repository.CollectivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final CollectivityRepository collectivityRepository;

    @Transactional
    public CollectivityResponse assignIdentity(String id, AssignIdentityRequest request) {
        CollectivityEntity collectivity = collectivityRepository.findById(id);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

         if (collectivityRepository.hasNumberAndName(id)) {
            throw new RuntimeException("Number and name already assigned, cannot be changed");
        }

         if (collectivityRepository.existsByNumber(request.getNumber())) {
            throw new RuntimeException("Number already exists");
        }

         if (collectivityRepository.existsByName(request.getName())) {
            throw new RuntimeException("Name already exists");
        }

         collectivityRepository.updateNumberAndName(id, request.getNumber(), request.getName());

         CollectivityEntity updated = collectivityRepository.findById(id);

         return buildResponse(updated);
    }

    private CollectivityResponse buildResponse(CollectivityEntity collectivity) {
        CollectivityResponse response = new CollectivityResponse();
        response.setId(collectivity.getId());
        response.setNumber(collectivity.getNumber());
        response.setName(collectivity.getName());
        response.setLocation(collectivity.getLocation());
        response.setCreationDate(collectivity.getCreationDate());
        response.setStatus(collectivity.getStatus().name());
        response.setMemberCount(collectivityRepository.countMembers(collectivity.getId()));
        return response;
    }
}