package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.SponsorshipEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class SponsorshipRepository {
    private final DataSource dataSource;
    public SponsorshipRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SponsorshipEntity save(SponsorshipEntity s) {
        String sql = "INSERT INTO sponsorship (member_id, sponsor_id, relationship, sponsorship_date) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getMemberId());
            ps.setString(2, s.getSponsorId());
            ps.setString(3, s.getRelationship());
            ps.setDate(4, Date.valueOf(s.getSponsorshipDate()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setId(rs.getString("id"));
                }
            }
            return s;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save sponsorship", e);
        }
    }
}