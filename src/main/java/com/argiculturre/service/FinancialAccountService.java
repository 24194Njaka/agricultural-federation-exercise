package com.argiculturre.service;

import com.argiculturre.config.DataSource;
import com.argiculturre.dto.FinancialAccount;
import com.argiculturre.exception.ResourceNotFoundException;
import com.argiculturre.repository.CollectivityRepository;
import com.argiculturre.repository.FinancialAccountRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialAccountService {

    private final DataSource dataSource;
    private final CollectivityRepository collectivityRepository;
    private final FinancialAccountRepository financialAccountRepository;

    public FinancialAccountService(DataSource dataSource,
                                   CollectivityRepository collectivityRepository,
                                   FinancialAccountRepository financialAccountRepository) {
        this.dataSource = dataSource;
        this.collectivityRepository = collectivityRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    public List<FinancialAccount> getFinancialAccounts(String collectivityIdStr, LocalDate atDate) {
        try (Connection conn = dataSource.getConnection()) {
            String collectivityId = collectivityIdStr;

            if (collectivityRepository.findById(conn, collectivityId).isEmpty()) {
                throw new ResourceNotFoundException("Collectivity not found with id: " + collectivityIdStr);
            }

            if (atDate != null) {
                return financialAccountRepository.findByCollectivityIdAndDate(conn, collectivityId, atDate);
            } else {
                return financialAccountRepository.findByCollectivityId(conn, collectivityId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching financial accounts", e);
        }
    }
}
