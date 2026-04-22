package com.collective.federation.repository;

 import com.collective.federation.entity.CollectivityEntity;
import com.collective.federation.entity.TypeStatus;
import org.springframework.stereotype.Repository;
import java.sql.*;


@Repository
public class CollectivityRepository {

    private Connection getConn() throws SQLException {
        return com.collective.federation.util.DataSourceConfig.getConnection();
    }

    public CollectivityEntity save(CollectivityEntity c) {
        String sql = "INSERT INTO collectivities (number, name, specialty, city, creation_date, status) VALUES (?,?,?,?,?,?::type_status) RETURNING id";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNumber());
            ps.setString(2, c.getName());
            ps.setString(3, c.getSpecialty());
            ps.setString(4, c.getCity());
            ps.setDate(5, Date.valueOf(c.getCreationDate()));
            ps.setString(6, c.getStatus().name());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) c.setId(rs.getLong("id"));
            }
            return c;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save collectivity", e);
        }
    }

    public CollectivityEntity findById(Long id) {
        String sql = "SELECT * FROM collectivities WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById", e);
        }
        return null;
    }

    public boolean existsByNumber(String number) {
        String sql = "SELECT 1 FROM collectivities WHERE number = ?";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur existsByName", e);
        }
    }

    public int countMembers(Long collectivityId) {
        String sql = "SELECT COUNT(*) FROM members WHERE collectivity_id = ?";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
        c.setSpecialty(rs.getString("specialty"));
        c.setCity(rs.getString("city"));
        c.setCreationDate(rs.getDate("creation_date").toLocalDate());
        c.setStatus(TypeStatus.valueOf(rs.getString("status")));
        c.setAuthorizationDate(rs.getDate("authorization_date") != null ? rs.getDate("authorization_date").toLocalDate() : null);
        return c;
    }
}