package com.argiculturre.repository;

import com.argiculturre.config.Datasource;
import com.argiculturre.entity.MemberEntity;
import com.argiculturre.entity.MemberRole;
import com.argiculturre.entity.TypeGender;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {

    public MemberEntity save(MemberEntity m) {
        String sql = "INSERT INTO members (first_name, last_name, birth_date, gender, address, profession, phone, email, membership_date, role, collectivity_id) VALUES (?, ?, ?, ?::type_gender, ?, ?, ?, ?, ?, ?::member_role, ?) RETURNING id";
        try (Connection conn = Datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getFirstName());
            ps.setString(2, m.getLastName());
            ps.setDate(3, Date.valueOf(m.getBirthDate()));
            ps.setString(4, m.getGender().name());
            ps.setString(5, m.getAddress());
            ps.setString(6, m.getProfession());
            ps.setString(7, m.getPhoneNumber());
            ps.setString(8, m.getEmail());
            ps.setDate(9, Date.valueOf(m.getMembershipDate()));
            ps.setString(10, m.getRole().name());
            if (m.getCollectivityId() != null) {
                ps.setLong(11, m.getCollectivityId());
            } else {
                ps.setNull(11, Types.BIGINT);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m.setId(rs.getLong("id"));
                }
            }
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save member", e);
        }
    }

    public MemberEntity findById(Long id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        try (Connection conn = Datasource.getConnection();
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

    public List<MemberEntity> findByIds(List<Long> ids) {
        List<MemberEntity> members = new ArrayList<>();
        if (ids == null || ids.isEmpty()) return members;

        String placeholders = String.join(",", ids.stream().map(id -> "?").toArray(String[]::new));
        String sql = "SELECT * FROM members WHERE id IN (" + placeholders + ")";

        try (Connection conn = Datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < ids.size(); i++) {
                ps.setLong(i + 1, ids.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    members.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findByIds", e);
        }
        return members;
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM members WHERE email = ?";
        try (Connection conn = Datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur existsByEmail", e);
        }
    }

    public List<MemberEntity> findConfirmedWithSeniority(LocalDate minDate) {
        List<MemberEntity> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE role = ?::member_role AND membership_date <= ?";
        try (Connection conn = Datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MemberRole.CONFIRMED_MEMBER.name());
            ps.setDate(2, Date.valueOf(minDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    members.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findConfirmedWithSeniority", e);
        }
        return members;
    }

    public void updateCollectivityId(Long memberId, Long collectivityId) {
        String sql = "UPDATE members SET collectivity_id = ? WHERE id = ?";
        try (Connection conn = Datasource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, collectivityId);
            ps.setLong(2, memberId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur updateCollectivityId", e);
        }
    }

    private MemberEntity map(ResultSet rs) throws SQLException {
        MemberEntity m = new MemberEntity();
        m.setId(rs.getLong("id"));
        m.setFirstName(rs.getString("first_name"));
        m.setLastName(rs.getString("last_name"));
        m.setBirthDate(rs.getDate("birth_date").toLocalDate());
        m.setGender(TypeGender.valueOf(rs.getString("gender")));
        m.setAddress(rs.getString("address"));
        m.setProfession(rs.getString("profession"));
        m.setPhoneNumber(rs.getString("phone"));
        m.setEmail(rs.getString("email"));
        m.setMembershipDate(rs.getDate("membership_date").toLocalDate());
        m.setRole(MemberRole.valueOf(rs.getString("role")));
        Long colId = rs.getLong("collectivity_id");
        if (!rs.wasNull()) {
            m.setCollectivityId(colId);
        }
        return m;
    }
}