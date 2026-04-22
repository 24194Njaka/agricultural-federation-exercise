package com.collective.federation.repository;
import com.collective.federation.entity.CollectivityEntity;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class CollectivityRepository {
    private final DataSource dataSource;
    public CollectivityRepository(DataSource dataSource) { this.dataSource = dataSource; }

    public void save(CollectivityEntity c) {
        String sql = "INSERT INTO collectivity (id, name, location, specialty, city, creation_date, status, authorization_date) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getLocation());
            ps.setString(4, c.getSpecialty());
            ps.setString(5, c.getCity());
            ps.setDate(6, Date.valueOf(c.getCreationDate()));
            ps.setString(7, c.getStatus().name());
            ps.setDate(8, Date.valueOf(c.getAuthorizationDate()));
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public boolean existsById(Long id) {
        String sql = "SELECT count(*) FROM collectivity WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() && rs.getInt(1) > 0; }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}