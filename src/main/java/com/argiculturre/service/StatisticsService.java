package com.argiculturre.service;

import com.argiculturre.dto.CollectivityGlobalStatistic;
import com.argiculturre.dto.CollectivityStatisticsResponse;
import com.argiculturre.dto.FederationStatisticsResponse;
import com.argiculturre.dto.MemberContributionStatistic;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.entity.MemberEntity;
import com.argiculturre.entity.MembershipFeeEntity;
import com.argiculturre.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;
    private final MembershipFeeRepository membershipFeeRepository;

    private double calculateTotalRequiredAmount(List<MembershipFeeEntity> activeFees, LocalDate from, LocalDate to) {
        double total = 0.0;
        for (MembershipFeeEntity fee : activeFees) {
            int occurrences = DateUtils.getOccurrenceCount(from, to, fee.getFrequency(), fee.getEligibleFrom());
            total += occurrences * fee.getAmount().doubleValue();
        }
        return total;
    }

    private double calculateRequiredPerMember(List<MembershipFeeEntity> activeFees, LocalDate from, LocalDate to) {
        double total = 0.0;
        for (MembershipFeeEntity fee : activeFees) {
            int occurrences = DateUtils.getOccurrenceCount(from, to, fee.getFrequency(), fee.getEligibleFrom());
            total += occurrences * fee.getAmount().doubleValue();
        }
        return total;
    }

    public CollectivityStatisticsResponse getCollectivityStatistics(String collectivityId, LocalDate from, LocalDate to) {
        CollectivityEntity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<MemberEntity> members = memberRepository.findByCollectivityId(collectivityId);
        List<MembershipFeeEntity> activeFees = membershipFeeRepository.findActiveByCollectivity(collectivityId);

         double totalRequiredAmount = calculateTotalRequiredAmount(activeFees, from, to);

        List<MemberContributionStatistic> memberStats = new ArrayList<>();

        for (MemberEntity member : members) {
            Double totalContributions = transactionRepository.getTotalContributionsByMember(member.getId(), from, to);

            double pendingAmount = Math.max(0, totalRequiredAmount - totalContributions);

            MemberContributionStatistic stats = new MemberContributionStatistic();
            stats.setMemberId(member.getId());
            stats.setMemberName(member.getFirstName() + " " + member.getLastName());
            stats.setTotalContribution(totalContributions);
            stats.setPendingAmount(pendingAmount);
            memberStats.add(stats);
        }

        CollectivityStatisticsResponse response = new CollectivityStatisticsResponse();
        response.setCollectivityId(collectivityId);
        response.setCollectivityName(collectivity.getName());
        response.setStartDate(from);
        response.setEndDate(to);
        response.setMemberContributionStatistics(memberStats);
        return response;
    }

    public FederationStatisticsResponse getFederationStatistics(LocalDate from, LocalDate to) {
        List<CollectivityEntity> collectivities = collectivityRepository.findAll();
        List<CollectivityGlobalStatistic> statsList = new ArrayList<>();

        for (CollectivityEntity collectivity : collectivities) {
            List<MemberEntity> members = memberRepository.findByCollectivityId(collectivity.getId());
            List<MembershipFeeEntity> activeFees = membershipFeeRepository.findActiveByCollectivity(collectivity.getId());

            double totalRequiredPerMember = calculateRequiredPerMember(activeFees, from, to);

            int membersUpToDate = 0;
            int newMembersCount = 0;

            for (MemberEntity member : members) {
                Double totalContributions = transactionRepository.getTotalContributionsByMember(member.getId(), from, to);

                if (totalContributions >= totalRequiredPerMember) {
                    membersUpToDate++;
                }

                if (member.getDateAdhesionFederation() != null &&
                        !member.getDateAdhesionFederation().isBefore(from) &&
                        !member.getDateAdhesionFederation().isAfter(to)) {
                    newMembersCount++;
                }
            }

            double complianceRate = members.size() > 0 ? (membersUpToDate * 100.0 / members.size()) : 0.0;

            CollectivityGlobalStatistic stats = new CollectivityGlobalStatistic();
            stats.setCollectivityId(collectivity.getId());
            stats.setCollectivityName(collectivity.getName());
            stats.setContributionComplianceRate(complianceRate);
            stats.setNewMembersCount(newMembersCount);
            statsList.add(stats);
        }

        FederationStatisticsResponse response = new FederationStatisticsResponse();
        response.setStartDate(from);
        response.setEndDate(to);
        response.setCollectivityStatistics(statsList);
        return response;
    }
}