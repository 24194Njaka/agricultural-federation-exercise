package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.AccountEntity;
import com.argiculturre.entity.TransactionEntity;
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
                    return mapAccount(rs);  // ← Renommé
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
                    accounts.add(mapAccount(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByEntity", e);
        }
        return accounts;
    }

    public List<AccountEntity> findByEntityWithBalanceAtDate(String entityType, Long entityId, LocalDate date) {
        List<AccountEntity> accounts = new ArrayList<>();
        String sql = "SELECT a.*, " +
                "COALESCE((SELECT SUM(CASE WHEN t.transaction_type = 'CONTRIBUTION' THEN t.amount ELSE -t.amount END) " +
                "FROM transactions t WHERE t.account_id = a.id AND t.transaction_date <= ?), 0) as balance_at_date " +
                "FROM accounts a WHERE a.entity_type = ? AND a.entity_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            ps.setString(2, entityType);
            ps.setLong(3, entityId);
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

    private AccountEntity mapAccount(ResultSet rs) throws SQLException {
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

    private TransactionEntity mapTransaction(ResultSet rs) throws SQLException {
        TransactionEntity t = new TransactionEntity();
        t.setId(rs.getLong("id"));
        t.setAccountId(rs.getLong("account_id"));
        t.setMemberId(rs.getLong("member_id"));
        t.setTransactionType(rs.getString("transaction_type"));
        t.setAmount(rs.getDouble("amount"));
        t.setPaymentMethod(rs.getString("payment_method"));
        t.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
        t.setDescription(rs.getString("description"));
        return t;
    }
}