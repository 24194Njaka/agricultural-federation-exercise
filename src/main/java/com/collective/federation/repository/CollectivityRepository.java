package com.collective.federation.repository;

import com.collective.federation.entity.Collectivity;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CollectivityRepository {
    private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Collectivity collectivity) {
        String sql = "INSERT INTO collectivity (id, name, location, specialty, federation_approval) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivity.getId());
            stmt.setString(2, collectivity.getName());
            stmt.setString(3, collectivity.getLocation());
            stmt.setString(4, collectivity.getSpecialty());
            stmt.setBoolean(5, collectivity.isFederationApproval());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
