package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.MembershipFeeEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipFeeRepository {

    private final DataSource dataSource;

    public MembershipFeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Méthode avec Connection (existante)
    public List<MembershipFeeEntity> findByCollectivityId(Connection conn, String collectivityId) throws SQLException {
        String sql = "SELECT id, collectivity_id, eligible_from, frequency, amount, label, status " +
                "FROM membership_fee WHERE collectivity_id = ? ORDER BY eligible_from DESC";
        List<MembershipFeeEntity> fees = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MembershipFeeEntity fee = new MembershipFeeEntity();
                fee.setId(rs.getString("id"));
                fee.setCollectivityId(rs.getString("collectivity_id"));
                fee.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                fee.setFrequency(rs.getString("frequency"));
                fee.setAmount(rs.getBigDecimal("amount"));
                fee.setLabel(rs.getString("label"));
                fee.setStatus(rs.getString("status"));
                fees.add(fee);
            }
        }
        return fees;
    }

    // AJOUTER CETTE MÉTHODE - sans Connection
    public List<MembershipFeeEntity> findActiveByCollectivity(String collectivityId) {
        List<MembershipFeeEntity> fees = new ArrayList<>();
        String sql = "SELECT id, collectivity_id, eligible_from, frequency, amount, label, status " +
                "FROM membership_fee WHERE collectivity_id = ? AND status = 'ACTIVE'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MembershipFeeEntity fee = new MembershipFeeEntity();
                    fee.setId(rs.getString("id"));
                    fee.setCollectivityId(rs.getString("collectivity_id"));
                    fee.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                    fee.setFrequency(rs.getString("frequency"));
                    fee.setAmount(rs.getBigDecimal("amount"));
                    fee.setLabel(rs.getString("label"));
                    fee.setStatus(rs.getString("status"));
                    fees.add(fee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findActiveByCollectivity: " + e.getMessage(), e);
        }
        return fees;
    }

    // Méthode avec Connection (existante) - garder pour compatibilité
    public List<MembershipFeeEntity> findActiveByCollectivityId(Connection conn, String collectivityId) throws SQLException {
        List<MembershipFeeEntity> fees = new ArrayList<>();
        String sql = "SELECT id, collectivity_id, eligible_from, frequency, amount, label, status " +
                "FROM membership_fee WHERE collectivity_id = ? AND status = 'ACTIVE'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MembershipFeeEntity fee = new MembershipFeeEntity();
                    fee.setId(rs.getString("id"));
                    fee.setCollectivityId(rs.getString("collectivity_id"));
                    fee.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                    fee.setFrequency(rs.getString("frequency"));
                    fee.setAmount(rs.getBigDecimal("amount"));
                    fee.setLabel(rs.getString("label"));
                    fee.setStatus(rs.getString("status"));
                    fees.add(fee);
                }
            }
        }
        return fees;
    }

    public void insert(Connection conn, MembershipFeeEntity fee) throws SQLException {
        String sql = "INSERT INTO membership_fee (id, collectivity_id, eligible_from, frequency, amount, label, status) " +
                "VALUES (?, ?, ?, CAST(? as frequency_enum), ?, ?, CAST(? as activity_status_enum))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fee.getId());
            stmt.setString(2, fee.getCollectivityId());
            stmt.setDate(3, Date.valueOf(fee.getEligibleFrom()));
            stmt.setString(4, fee.getFrequency());
            stmt.setBigDecimal(5, fee.getAmount());
            stmt.setString(6, fee.getLabel());
            stmt.setString(7, fee.getStatus() != null ? fee.getStatus() : "ACTIVE");
            stmt.executeUpdate();
        }
    }

    public boolean existsDuplicate(Connection conn, String collectivityId, LocalDate eligibleFrom, String frequency, BigDecimal amount) throws SQLException {
        String sql = "SELECT 1 FROM membership_fee WHERE collectivity_id = ? AND eligible_from = ? AND frequency = ? AND amount = ? LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(eligibleFrom));
            stmt.setString(3, frequency);
            stmt.setBigDecimal(4, amount);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public Optional<MembershipFeeEntity> findById(Connection conn, String id) throws SQLException {
        String sql = "SELECT id, collectivity_id, eligible_from, frequency, amount, label, status " +
                "FROM membership_fee WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MembershipFeeEntity fee = new MembershipFeeEntity();
                fee.setId(rs.getString("id"));
                fee.setCollectivityId(rs.getString("collectivity_id"));
                fee.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                fee.setFrequency(rs.getString("frequency"));
                fee.setAmount(rs.getBigDecimal("amount"));
                fee.setLabel(rs.getString("label"));
                fee.setStatus(rs.getString("status"));
                return Optional.of(fee);
            }
            return Optional.empty();
        }
    }
}