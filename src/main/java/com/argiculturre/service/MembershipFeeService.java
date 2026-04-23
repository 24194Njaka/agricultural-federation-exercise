package com.argiculturre.service;

import com.argiculturre.dto.request.CreateMembershipFeeRequest;
import com.argiculturre.dto.response.MembershipFeeResponse;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.entity.MembershipFeeEntity;
import com.argiculturre.repository.CollectivityRepository;
import com.argiculturre.repository.MembershipFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipFeeService {

    private final MembershipFeeRepository feeRepository;
    private final CollectivityRepository collectivityRepository;

    @Transactional
    public List<MembershipFeeResponse> createMembershipFees(String collectivityId, List<CreateMembershipFeeRequest> requests) {
        CollectivityEntity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        return requests.stream()
                .map(req -> createFee(collectivityId, req))
                .collect(Collectors.toList());
    }

    private MembershipFeeResponse createFee(String collectivityId, CreateMembershipFeeRequest request) {
        MembershipFeeEntity fee = new MembershipFeeEntity();
        fee.setId(generateId());
        fee.setCollectivityId(collectivityId);
        fee.setLabel(request.getLabel());
        fee.setAmount(request.getAmount());
        fee.setFrequency(request.getFrequency());
        fee.setStartDate(request.getStartDate());
        fee.setEndDate(request.getEndDate());
        fee.setDescription(request.getDescription());

        MembershipFeeEntity saved = feeRepository.save(fee);
        return mapToResponse(saved);
    }

    public List<MembershipFeeResponse> getMembershipFees(String collectivityId) {
        CollectivityEntity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<MembershipFeeEntity> fees = feeRepository.findByCollectivityId(collectivityId);
        return fees.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private String generateId() {
        return "FEE-" + System.currentTimeMillis();
    }

    private MembershipFeeResponse mapToResponse(MembershipFeeEntity fee) {
        MembershipFeeResponse response = new MembershipFeeResponse();
        response.setId(fee.getId());
        response.setLabel(fee.getLabel());
        response.setAmount(fee.getAmount());
        response.setFrequency(fee.getFrequency());
        response.setStartDate(fee.getStartDate());
        response.setEndDate(fee.getEndDate());
        response.setDescription(fee.getDescription());
        return response;
    }
}