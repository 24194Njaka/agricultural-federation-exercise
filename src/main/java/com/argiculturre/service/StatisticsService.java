//package com.argiculturre.service;
//
//import com.argiculturre.dto.request.MemberContributionStatistic;
//import com.argiculturre.dto.response.*;
//import com.argiculturre.entity.*;
//import com.argiculturre.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class StatisticsService {
//
//    private final CollectivityRepository collectivityRepository;
//    private final MemberRepository memberRepository;
//    private final TransactionRepository transactionRepository;
//    private final MembershipFeeRepository membershipFeeRepository;
//
//    // GET /collectivities/{id}/statistics
//    public CollectivityStatisticsResponse getCollectivityStatistics(String collectivityId, LocalDate from, LocalDate to) {
//        CollectivityEntity collectivity = collectivityRepository.findById(collectivityId);
//        if (collectivity == null) {
//            throw new RuntimeException("Collectivity not found");
//        }
//
//        List<MemberEntity> members = memberRepository.findByCollectivityId(collectivityId);
//        List<MemberContributionStatistic> memberStats = new ArrayList<>();
//
//        // Récupérer les cotisations actives
//        List<MembershipFeeEntity> activeFees = membershipFeeRepository.findActiveByCollectivity(collectivityId);
//        double totalRequiredAmount = activeFees.stream()
//                .mapToDouble(MembershipFeeEntity::getAmount)
//                .sum();
//
//        for (MemberEntity member : members) {
//            // Montant encaissé
//            Double totalContributions = transactionRepository.getTotalContributionsByMember(member.getId(), from, to);
//
//            // Montant impayé = cotisations actives - montant déjà payé
//            double pendingAmount = Math.max(0, totalRequiredAmount - totalContributions);
//
//            MemberContributionStatistic stats = new MemberContributionStatistic();
//            stats.setMemberId(member.getId());
//            stats.setMemberName(member.getFirstName() + " " + member.getLastName());
//            stats.setTotalContribution(totalContributions);
//            stats.setPendingAmount(pendingAmount);
//            memberStats.add(stats);
//        }
//
//        CollectivityStatisticsResponse response = new CollectivityStatisticsResponse();
//        response.setCollectivityId(collectivityId);
//        response.setCollectivityName(collectivity.getName());
//        response.setStartDate(from);
//        response.setEndDate(to);
//        response.setMemberContributionStatistics(memberStats);
//        return response;
//    }
//
//    // GET /collectivities/statistics
//    public FederationStatisticsResponse getFederationStatistics(LocalDate from, LocalDate to) {
//        List<CollectivityEntity> collectivities = collectivityRepository.findAll();
//        List<CollectivityGlobalStatistc> statsList = new ArrayList<>();
//
//        for (CollectivityEntity collectivity : collectivities) {
//            List<MemberEntity> members = memberRepository.findByCollectivityId(collectivity.getId());
//            List<MembershipFeeEntity> activeFees = membershipFeeRepository.findActiveByCollectivityId(collectivity.getId());
//
//            // Calcul du montant total requis par membre
//            double totalRequiredPerMember = activeFees.stream()
//                    .mapToDouble(MembershipFeeEntity::getAmount)
//                    .sum();
//
//            // Compter les membres à jour (payé >= requis)
//            int membersUpToDate = 0;
//            int newMembersCount = 0;
//
//            for (MemberEntity member : members) {
//                Double totalContributions = transactionRepository.getTotalContributionsByMember(member.getId(), from, to);
//
//                if (totalContributions >= totalRequiredPerMember) {
//                    membersUpToDate++;
//                }
//
//                // Nouveaux adhérents (membres inscrits pendant la période)
//                if (member.getMembershipDate() != null &&
//                        !member.getMembershipDate().isBefore(from) &&
//                        !member.getMembershipDate().isAfter(to)) {
//                    newMembersCount++;
//                }
//            }
//
//            double complianceRate = members.size() > 0 ? (membersUpToDate * 100.0 / members.size()) : 0.0;
//
//            CollectivityGlobalStatistics stats = new CollectivityGlobalStatistics();
//            stats.setCollectivityId(collectivity.getId());
//            stats.setCollectivityName(collectivity.getName());
//            stats.setContributionComplianceRate(complianceRate);
//            stats.setNewMembersCount(newMembersCount);
//            statsList.add(stats);
//        }
//
//        FederationStatisticsResponse response = new FederationStatisticsResponse();
//        response.setStartDate(from);
//        response.setEndDate(to);
//        response.setCollectivityStatistics(statsList);
//        return response;
//    }
//}