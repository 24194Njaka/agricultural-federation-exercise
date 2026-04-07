package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.AccountEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final DataSource dataSource;

    public AccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AccountEntity save(AccountEntity account) {
        String sql = "INSERT INTO account (id, entity_id, account_type, account_name, account_holder_name, " +
                "bank_name, mobile_money_service, phone_number, balance, currency) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getId());                    // id
            ps.setString(2, account.getEntityId());              // entity_id
            ps.setString(3, account.getAccountType());           // account_type
            ps.setString(4, account.getAccountName());           // account_name
            ps.setString(5, account.getAccountHolderName());     // account_holder_name
            ps.setString(6, account.getBankName());              // bank_name
            ps.setString(7, account.getMobileMoneyService());    // mobile_money_service
            ps.setString(8, account.getPhoneNumber());           // phone_number
            ps.setDouble(9, account.getBalance() != null ? account.getBalance() : 0.0);  // balance
            ps.setString(10, account.getCurrency() != null ? account.getCurrency() : "MGA");  // currency

            ps.executeUpdate();
            return account;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save account: " + e.getMessage(), e);
        }
    }

    public AccountEntity findById(String id) {
        String sql = "SELECT id, entity_id, account_type, account_name, account_holder_name, bank_name, mobile_money_service, phone_number, balance, currency FROM account WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAccount(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById account", e);
        }
        return null;
    }

    public List<AccountEntity> findByEntity(String entityType, String entityId) {
        List<AccountEntity> accounts = new ArrayList<>();
        String sql = "SELECT id, entity_id, account_type, account_name, account_holder_name, " +
                "bank_name, mobile_money_service, phone_number, balance, currency " +
                "FROM account WHERE entity_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accounts.add(mapAccount(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByEntity", e);
        }
        return accounts;
    }

    public List<AccountEntity> findByEntityWithBalanceAtDate(String entityType, String entityId, LocalDate date) {
        List<AccountEntity> accounts = new ArrayList<>();
        String sql = "SELECT a.id, a.entity_id, a.account_type, a.account_name, a.account_holder_name, " +
                "a.bank_name, a.mobile_money_service, a.phone_number, a.balance, a.currency, " +
                "COALESCE((SELECT SUM(CASE WHEN t.transaction_type = 'CONTRIBUTION' THEN t.amount ELSE -t.amount END) " +
                "FROM transaction t WHERE t.account_id = a.id AND t.transaction_date <= ?), 0) as balance_at_date " +
                "FROM account a WHERE a.entity_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            ps.setString(2, entityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AccountEntity account = mapAccount(rs);
                    account.setBalance(rs.getDouble("balance_at_date"));
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByEntityWithBalanceAtDate", e);
        }
        return accounts;
    }

    public boolean hasCashAccount(String entityType, String entityId) {
        String sql = "SELECT 1 FROM account WHERE entity_id = ? AND account_type = 'CASH'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entityId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur hasCashAccount", e);
        }
    }

    public void updateBalance(String id, Double newBalance) {
        String sql = "UPDATE account SET balance = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur updateBalance", e);
        }
    }

    public List<AccountEntity> findAll() {
        List<AccountEntity> accounts = new ArrayList<>();
        String sql = "SELECT id, entity_id, account_type, account_name, account_holder_name, " +
                "bank_name, mobile_money_service, phone_number, balance, currency FROM account ORDER BY id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                accounts.add(mapAccount(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll accounts", e);
        }
        return accounts;
    }

    private AccountEntity mapAccount(ResultSet rs) throws SQLException {
        AccountEntity a = new AccountEntity();
        a.setId(rs.getString("id"));
        a.setEntityId(rs.getString("entity_id"));
        a.setAccountType(rs.getString("account_type"));
        a.setAccountName(rs.getString("account_name"));
        a.setAccountHolderName(rs.getString("account_holder_name"));
        a.setBankName(rs.getString("bank_name"));
        a.setMobileMoneyService(rs.getString("mobile_money_service"));
        a.setPhoneNumber(rs.getString("phone_number"));
        a.setBalance(rs.getDouble("balance"));
        a.setCurrency(rs.getString("currency"));
        return a;
    }

}