package com.collective.federation.repository;

import com.collective.federation.entity.Collectivity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CollectivityRepository {
    private final JdbcTemplate jdbc;

    public CollectivityRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Collectivity collectivity) {
        String sql = "INSERT INTO collectivity (id, name, location, specialty, federation_approval) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql,
                collectivity.getId(),
                collectivity.getName(),
                collectivity.getLocation(),
                collectivity.getSpecialty(),
                collectivity.isFederationApproval()
        );
    }

    public boolean existsById(String id) {
        String sql = "SELECT count(*) FROM collectivity WHERE id = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
