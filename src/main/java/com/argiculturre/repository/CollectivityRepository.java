package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.CollectivityEntity;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CollectivityRepository {

    private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Connection conn, CollectivityEntity collectivity) throws SQLException {
        String sql = "INSERT INTO collectivity (id, location, specialite_agricole, annual_dues_amount, date_creation, federation_approval, name, number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivity.getId());
            stmt.setString(2, collectivity.getLocation());
            stmt.setString(3, collectivity.getSpecialiteAgricole());
            stmt.setInt(4, collectivity.getAnnualDuesAmount());
            stmt.setDate(5, Date.valueOf(collectivity.getDateCreation()));
            stmt.setBoolean(6, collectivity.getFederationApproval());
            stmt.setString(7, collectivity.getName());
            if (collectivity.getNumber() != null) {
                stmt.setInt(8, collectivity.getNumber());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    public CollectivityEntity findById(String id) {
        String sql = "SELECT id, location, specialite_agricole, annual_dues_amount, date_creation, federation_approval, name, number " +
                "FROM collectivity WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CollectivityEntity c = new CollectivityEntity();
                c.setId(rs.getString("id"));
                c.setLocation(rs.getString("location"));
                c.setSpecialiteAgricole(rs.getString("specialite_agricole"));
                c.setAnnualDuesAmount(rs.getInt("annual_dues_amount"));
                c.setDateCreation(rs.getDate("date_creation").toLocalDate());
                c.setFederationApproval(rs.getBoolean("federation_approval"));
                c.setName(rs.getString("name"));
                int number = rs.getInt("number");
                c.setNumber(rs.wasNull() ? null : number);
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById: " + e.getMessage(), e);
        }
    }

    public List<CollectivityEntity> findAll() {
        List<CollectivityEntity> collectivities = new ArrayList<>();
        String sql = "SELECT id, location, specialite_agricole, annual_dues_amount, date_creation, federation_approval, name, number " +
                "FROM collectivity";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CollectivityEntity c = new CollectivityEntity();
                c.setId(rs.getString("id"));
                c.setLocation(rs.getString("location"));
                c.setSpecialiteAgricole(rs.getString("specialite_agricole"));
                c.setAnnualDuesAmount(rs.getInt("annual_dues_amount"));
                c.setDateCreation(rs.getDate("date_creation").toLocalDate());
                c.setFederationApproval(rs.getBoolean("federation_approval"));
                c.setName(rs.getString("name"));
                int number = rs.getInt("number");
                c.setNumber(rs.wasNull() ? null : number);
                collectivities.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll: " + e.getMessage(), e);
        }
        return collectivities;
    }

    public Optional<CollectivityEntity> findById(Connection conn, String id) throws SQLException {
        String sql = "SELECT id, location, specialite_agricole, annual_dues_amount, date_creation, federation_approval, name, number " +
                "FROM collectivity WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CollectivityEntity c = new CollectivityEntity();
                c.setId(rs.getString("id"));
                c.setLocation(rs.getString("location"));
                c.setSpecialiteAgricole(rs.getString("specialite_agricole"));
                c.setAnnualDuesAmount(rs.getInt("annual_dues_amount"));
                c.setDateCreation(rs.getDate("date_creation").toLocalDate());
                c.setFederationApproval(rs.getBoolean("federation_approval"));
                c.setName(rs.getString("name"));
                int number = rs.getInt("number");
                c.setNumber(rs.wasNull() ? null : number);
                return Optional.of(c);
            }
            return Optional.empty();
        }
    }

    public boolean hasNameAndNumber(Connection conn, String id) throws SQLException {
        String sql = "SELECT name, number FROM collectivity WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name") != null && rs.getObject("number") != null;
            }
            return false;
        }
    }

    public void updateNameAndNumber(Connection conn, String id, String name, Integer number) throws SQLException {
        String sql = "UPDATE collectivity SET name = ?, number = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            if (number != null) {
                stmt.setInt(2, number);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, id);
            int updated = stmt.executeUpdate();
            if (updated == 0) throw new SQLException("Collectivity not found");
        }
    }

    public boolean isNameUsed(Connection conn, String name, String excludeId) throws SQLException {
        String sql = "SELECT 1 FROM collectivity WHERE name = ? AND id != ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, excludeId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean isNumberUsed(Connection conn, Integer number, String excludeId) throws SQLException {
        String sql = "SELECT 1 FROM collectivity WHERE number = ? AND id != ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, number);
            stmt.setString(2, excludeId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public Optional<CollectivityEntity> findByIdWithDetails(Connection conn, String id) throws SQLException {
        String sql = "SELECT id, location, name, number, specialite_agricole, annual_dues_amount, date_creation, federation_approval " +
                "FROM collectivity WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CollectivityEntity entity = new CollectivityEntity();
                entity.setId(rs.getString("id"));
                entity.setLocation(rs.getString("location"));
                entity.setName(rs.getString("name"));
                int number = rs.getInt("number");
                entity.setNumber(rs.wasNull() ? null : number);
                entity.setSpecialiteAgricole(rs.getString("specialite_agricole"));
                entity.setAnnualDuesAmount(rs.getInt("annual_dues_amount"));
                entity.setDateCreation(rs.getDate("date_creation").toLocalDate());
                entity.setFederationApproval(rs.getBoolean("federation_approval"));
                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }
}