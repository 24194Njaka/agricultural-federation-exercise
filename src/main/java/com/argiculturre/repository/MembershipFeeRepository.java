package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.MembershipFeeEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {

    private final DataSource dataSource;

    public MembershipFeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MembershipFeeEntity save(MembershipFeeEntity fee) {
        String sql = "INSERT INTO membership_fee (id, collectivity_id, label, amount, frequency, start_date, end_date, description, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fee.getId());
            ps.setString(2, fee.getCollectivityId());
            ps.setString(3, fee.getLabel());
            ps.setDouble(4, fee.getAmount());
            ps.setString(5, fee.getFrequency());
            ps.setDate(6, fee.getStartDate() != null ? Date.valueOf(fee.getStartDate()) : null);
            ps.setDate(7, fee.getEndDate() != null ? Date.valueOf(fee.getEndDate()) : null);
            ps.setString(8, fee.getDescription());
            ps.setTimestamp(9, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
            ps.executeUpdate();
            return fee;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save membership fee: " + e.getMessage(), e);
        }
    }

    public List<MembershipFeeEntity> findByCollectivityId(String collectivityId) {
        List<MembershipFeeEntity> fees = new ArrayList<>();
        String sql = "SELECT id, collectivity_id, label, amount, frequency, start_date, end_date, description, created_at " +
                "FROM membership_fee WHERE collectivity_id = ? ORDER BY start_date DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fees.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByCollectivityId: " + e.getMessage(), e);
        }
        return fees;
    }

    private MembershipFeeEntity map(ResultSet rs) throws SQLException {
        MembershipFeeEntity fee = new MembershipFeeEntity();
        fee.setId(rs.getString("id"));
        fee.setCollectivityId(rs.getString("collectivity_id"));
        fee.setLabel(rs.getString("label"));
        fee.setAmount(rs.getDouble("amount"));
        fee.setFrequency(rs.getString("frequency"));

        Date startDate = rs.getDate("start_date");
        if (startDate != null) {
            fee.setStartDate(startDate.toLocalDate());
        }

        Date endDate = rs.getDate("end_date");
        if (endDate != null) {
            fee.setEndDate(endDate.toLocalDate());
        }

        fee.setDescription(rs.getString("description"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            fee.setCreatedAt(createdAt.toLocalDateTime().toLocalDate());
        }

        return fee;
    }
}