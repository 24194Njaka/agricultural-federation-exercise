package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.TransactionEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private final DataSource dataSource;

    public TransactionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public TransactionEntity save(TransactionEntity transaction) {
        String sql = "INSERT INTO transactions (account_id, member_id, transaction_type, amount, payment_method, transaction_date, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, transaction.getAccountId());
            ps.setLong(2, transaction.getMemberId());
            ps.setString(3, transaction.getTransactionType());
            ps.setDouble(4, transaction.getAmount());
            ps.setString(5, transaction.getPaymentMethod());
            ps.setDate(6, Date.valueOf(transaction.getTransactionDate()));
            ps.setString(7, transaction.getDescription());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    transaction.setId(rs.getLong("id"));
                }
            }
            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save transaction", e);
        }
    }

    public List<TransactionEntity> findByCollectivityId(Long collectivityId, LocalDate from, LocalDate to) {
        List<TransactionEntity> transactions = new ArrayList<>();
        String sql = "SELECT t.* FROM transactions t " +
                "JOIN accounts a ON t.account_id = a.id " +
                "WHERE a.entity_type = 'COLLECTIVITY' AND a.entity_id = ? " +
                "AND t.transaction_date BETWEEN ? AND ? " +
                "ORDER BY t.transaction_date DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, collectivityId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transactions.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByCollectivityId", e);
        }
        return transactions;
    }

    private TransactionEntity map(ResultSet rs) throws SQLException {
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