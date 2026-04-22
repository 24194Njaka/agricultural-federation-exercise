package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.AccountEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final DataSource dataSource;

    public AccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AccountEntity save(AccountEntity account) {
        String sql = "INSERT INTO accounts (entity_type, entity_id, account_type, account_name, account_holder_name, " +
                "bank_name, bank_code, branch_code, account_number, rib_key, mobile_money_service, phone_number, balance, currency) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getEntityType());
            ps.setLong(2, account.getEntityId());
            ps.setString(3, account.getAccountType());
            ps.setString(4, account.getAccountName());
            ps.setString(5, account.getAccountHolderName());
            ps.setString(6, account.getBankName());
            ps.setString(7, account.getBankCode());
            ps.setString(8, account.getBranchCode());
            ps.setString(9, account.getAccountNumber());
            ps.setString(10, account.getRibKey());
            ps.setString(11, account.getMobileMoneyService());
            ps.setString(12, account.getPhoneNumber());
            ps.setDouble(13, account.getBalance() != null ? account.getBalance() : 0.0);
            ps.setString(14, account.getCurrency() != null ? account.getCurrency() : "MGA");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account.setId(rs.getLong("id"));
                }
            }
            return account;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save account", e);
        }
    }

    public AccountEntity findById(Long id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById", e);
        }
        return null;
    }

    public List<AccountEntity> findByEntity(String entityType, Long entityId) {
        List<AccountEntity> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE entity_type = ? AND entity_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entityType);
            ps.setLong(2, entityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accounts.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByEntity", e);
        }
        return accounts;
    }

    public boolean hasCashAccount(String entityType, Long entityId) {
        String sql = "SELECT 1 FROM accounts WHERE entity_type = ? AND entity_id = ? AND account_type = 'CASH'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entityType);
            ps.setLong(2, entityId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur hasCashAccount", e);
        }
    }

    public void updateBalance(Long id, Double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur updateBalance", e);
        }
    }

    private AccountEntity map(ResultSet rs) throws SQLException {
        AccountEntity a = new AccountEntity();
        a.setId(rs.getLong("id"));
        a.setEntityType(rs.getString("entity_type"));
        a.setEntityId(rs.getLong("entity_id"));
        a.setAccountType(rs.getString("account_type"));
        a.setAccountName(rs.getString("account_name"));
        a.setAccountHolderName(rs.getString("account_holder_name"));
        a.setBankName(rs.getString("bank_name"));
        a.setBankCode(rs.getString("bank_code"));
        a.setBranchCode(rs.getString("branch_code"));
        a.setAccountNumber(rs.getString("account_number"));
        a.setRibKey(rs.getString("rib_key"));
        a.setMobileMoneyService(rs.getString("mobile_money_service"));
        a.setPhoneNumber(rs.getString("phone_number"));
        a.setBalance(rs.getDouble("balance"));
        a.setCurrency(rs.getString("currency"));
        return a;
    }
}