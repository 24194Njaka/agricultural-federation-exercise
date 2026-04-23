package com.argiculturre.repository;

import com.argiculturre.config.DataSource;
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
    private final DataSource dataSource;
    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MemberEntity save(MemberEntity m) {
        String sql = "INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, membership_date, role, collectivity_id) VALUES (?, ?, ?, ?, ?::type_gender, ?, ?, ?, ?, ?, ?::member_role, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getId());
            ps.setString(2, m.getFirstName());
            ps.setString(3, m.getLastName());
            ps.setDate(4, Date.valueOf(m.getBirthDate()));
            ps.setString(5, m.getGender().name());
            ps.setString(6, m.getAddress());
            ps.setString(7, m.getProfession());
            ps.setString(8, m.getPhoneNumber());
            ps.setString(9, m.getEmail());
            ps.setDate(10, Date.valueOf(m.getMembershipDate()));
            ps.setString(11, m.getRole().name());
            if (m.getCollectivityId() != null) {
                ps.setString(12, m.getCollectivityId());
            } else {
                ps.setNull(12, Types.VARCHAR);
            }
            ps.executeUpdate();
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur save member", e);
        }
    }

    public MemberEntity findById(String id) {
        String sql = "SELECT id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, membership_date, role, collectivity_id FROM member WHERE id = ?";
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

    public List<MemberEntity> findByIds(List<String> ids) {
        List<MemberEntity> members = new ArrayList<>();
        if (ids == null || ids.isEmpty()) return members;

        String placeholders = String.join(",", ids.stream().map(id -> "?").toArray(String[]::new));
        String sql = "SELECT id, first_name, last_name, birth_date, gender, address, profession, phone, email, membership_date, role, collectivity_id FROM members WHERE id IN (" + placeholders + ")";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < ids.size(); i++) {
                ps.setString(i + 1, ids.get(i));
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
        String sql = "SELECT 1 FROM member WHERE email = ?";
        try (Connection conn = dataSource.getConnection();
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
        String sql = "SELECT id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, membership_date, role, collectivity_id FROM member WHERE role = ?::member_role AND membership_date <= ?";
        try (Connection conn = dataSource.getConnection();
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
        String sql = "UPDATE member SET collectivity_id = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
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
        m.setId(rs.getString("id"));
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
        String colId = rs.getString("collectivity_id");
        if (colId != null && !rs.wasNull()) {
            m.setCollectivityId(colId);
        }
        return m;
    }

    public void updateRoleAndCollectivity(String memberId, MemberRole role, String collectivityId) {
        String sql = "UPDATE member SET role = ?::member_role, collectivity_id = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, role.name());
            if (collectivityId != null) {
                ps.setString(2, collectivityId);
            } else {
                ps.setNull(2, Types.VARCHAR);
            }
            ps.setString(3, memberId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur updateRoleAndCollectivity", e);
        }
    }

    public List<MemberEntity> findAll() {
        List<MemberEntity> members = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, membership_date, role, collectivity_id FROM member ORDER BY id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                members.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll members", e);
        }
        return members;
    }
}