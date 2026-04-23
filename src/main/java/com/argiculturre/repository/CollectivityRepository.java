package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.entity.TypeStatus;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityRepository {

    private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CollectivityEntity save(CollectivityEntity c) {
        String sql = "INSERT INTO collectivity (id, number, name, location, specialisation, creation_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getId());
            ps.setString(2, c.getNumber());
            ps.setString(3, c.getName());
            ps.setString(4, c.getLocation());
            ps.setString(5, c.getSpecialisation());
            ps.setDate(6, Date.valueOf(c.getCreationDate()));
            ps.setString(7, c.getStatus().name());
            ps.executeUpdate();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save collectivity", e);
        }
    }

    public CollectivityEntity findById(String id) {
        // CORRIGÉ: espace après location,
        String sql = "SELECT id, number, name, location, specialisation, creation_date, status FROM collectivity WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById", e);
        }
        return null;
    }

    public boolean existsByNumber(String number) {
        String sql = "SELECT 1 FROM collectivity WHERE number = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur existsByNumber", e);
        }
    }

    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM collectivity WHERE name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur existsByName", e);
        }
    }

    public void updateNumberAndName(String id, String number, String name) {
        String sql = "UPDATE collectivity SET number = ?, name = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, number);
            ps.setString(2, name);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur updateNumberAndName", e);
        }
    }

    public boolean hasNumberAndName(String id) {
        String sql = "SELECT number, name FROM collectivity WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("number") != null && rs.getString("name") != null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur hasNumberAndName", e);
        }
        return false;
    }

    public int countMembers(String collectivityId) {
        String sql = "SELECT COUNT(id) FROM member WHERE collectivity_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur countMembers", e);
        }
        return 0;
    }

    public List<CollectivityEntity> findAll() {
        List<CollectivityEntity> collectivities = new ArrayList<>();
        String sql = "SELECT id, number, name, location, specialisation, creation_date, status FROM collectivity";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                collectivities.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll", e);
        }
        return collectivities;
    }

    private CollectivityEntity map(ResultSet rs) throws SQLException {
        System.out.println("=== MAP DEBUG ===");
        System.out.println("id: " + rs.getString("id"));
        System.out.println("name: " + rs.getString("name"));
        System.out.println("specialisation: " + rs.getString("specialisation"));
        System.out.println("================");

        CollectivityEntity c = new CollectivityEntity();
        c.setId(rs.getString("id"));
        c.setNumber(rs.getString("number"));
        c.setName(rs.getString("name"));
        c.setLocation(rs.getString("location"));
        c.setSpecialisation(rs.getString("specialisation"));
        c.setCreationDate(rs.getDate("creation_date").toLocalDate());
        c.setStatus(TypeStatus.valueOf(rs.getString("status")));
        return c;
    }
}