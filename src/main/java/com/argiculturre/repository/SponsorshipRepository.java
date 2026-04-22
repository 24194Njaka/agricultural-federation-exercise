package com.argiculturre.repository;

import com.argiculturre.config.Datasource;
import com.argiculturre.entity.SponsorshipEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class SponsorshipRepository {

    public SponsorshipEntity save(SponsorshipEntity s) {
        String sql = "INSERT INTO sponsorships (member_id, sponsor_id, relationship, sponsorship_date) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = Datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, s.getMemberId());
            ps.setLong(2, s.getSponsorId());
            ps.setString(3, s.getRelationship());
            ps.setDate(4, Date.valueOf(s.getSponsorshipDate()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setId(rs.getLong("id"));
                }
            }
            return s;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save sponsorship", e);
        }
    }
}