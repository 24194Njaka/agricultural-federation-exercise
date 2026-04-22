package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.entity.TypeStatus;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class CollectivityRepository {
    private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CollectivityEntity save(CollectivityEntity c) {
        String sql = "INSERT INTO collectivities (location, creation_date, status) VALUES (?, ?, ?::type_status) RETURNING id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getLocation());
            ps.setDate(2, Date.valueOf(c.getCreationDate()));
            ps.setString(3, c.getStatus().name());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c.setId(rs.getLong("id"));
                }
            }
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save collectivity", e);
        }
    }

    public CollectivityEntity findById(Long id) {
        String sql = "SELECT id, number, name, location, creation_date, status FROM collectivities WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
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
        String sql = "SELECT 1 FROM collectivities WHERE number = ?";
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
        String sql = "SELECT 1 FROM collectivities WHERE name = ?";
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

    public void updateNumberAndName(Long id, String number, String name) {
        String sql = "UPDATE collectivities SET number = ?, name = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, number);
            ps.setString(2, name);
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur updateNumberAndName", e);
        }
    }

    public boolean hasNumberAndName(Long id) {
        String sql = "SELECT number, name FROM collectivities WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
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

    public int countMembers(Long collectivityId) {
        String sql = "SELECT COUNT(*) FROM members WHERE collectivity_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur countMembers", e);
        }
        return 0;
    }

    private CollectivityEntity map(ResultSet rs) throws SQLException {
        CollectivityEntity c = new CollectivityEntity();
        c.setId(rs.getLong("id"));
        c.setNumber(rs.getString("number"));
        c.setName(rs.getString("name"));
        c.setLocation(rs.getString("location"));
        c.setCreationDate(rs.getDate("creation_date").toLocalDate());
        c.setStatus(TypeStatus.valueOf(rs.getString("status")));
        return c;
    }
}