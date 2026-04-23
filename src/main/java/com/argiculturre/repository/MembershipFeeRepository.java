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
        String sql = "INSERT INTO membership_fees (collectivity_id, name, amount, frequency, start_date, end_date, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, fee.getCollectivityId());
            ps.setString(2, fee.getName());
            ps.setDouble(3, fee.getAmount());
            ps.setString(4, fee.getFrequency());
            ps.setDate(5, Date.valueOf(fee.getStartDate()));
            ps.setDate(6, fee.getEndDate() != null ? Date.valueOf(fee.getEndDate()) : null);
            ps.setString(7, fee.getDescription());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fee.setId(rs.getLong("id"));
                }
            }
            return fee;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save membership fee", e);
        }
    }

    public List<MembershipFeeEntity> findByCollectivityId(Long collectivityId) {
        List<MembershipFeeEntity> fees = new ArrayList<>();
        String sql = "SELECT id, collectivity_id, name, amount, frequency, start_date, end_date, description FROM membership_fees WHERE collectivity_id = ? ORDER BY start_date DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fees.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByCollectivityId", e);
        }
        return fees;
    }

    private MembershipFeeEntity map(ResultSet rs) throws SQLException {
        MembershipFeeEntity fee = new MembershipFeeEntity();
        fee.setId(rs.getLong("id"));
        fee.setCollectivityId(rs.getLong("collectivity_id"));
        fee.setName(rs.getString("name"));
        fee.setAmount(rs.getDouble("amount"));
        fee.setFrequency(rs.getString("frequency"));
        fee.setStartDate(rs.getDate("start_date").toLocalDate());
        Date endDate = rs.getDate("end_date");
        if (endDate != null) {
            fee.setEndDate(endDate.toLocalDate());
        }
        fee.setDescription(rs.getString("description"));
        return fee;
    }
}