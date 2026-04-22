package com.collective.federation.repository;

import com.collective.federation.config.DataSourceConfig;
import com.collective.federation.entity.SponsorshipEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class SponsorshipRepository {

    private Connection getConn() throws SQLException {
        return DataSourceConfig.getConnection();
    }

    public SponsorshipEntity save(SponsorshipEntity s) {
        String sql = "INSERT INTO sponsorships (member_id, sponsor_id, relationship, sponsorship_date) VALUES (?,?,?,?) RETURNING id";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, s.getMemberId());
            ps.setLong(2, s.getSponsorId());
            ps.setString(3, s.getRelationship());
            ps.setDate(4, Date.valueOf(s.getSponsorshipDate()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) s.setId(rs.getLong("id"));
            }
            return s;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save sponsorship", e);
        }
    }
}