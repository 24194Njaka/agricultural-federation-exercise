package com.collective.federation.repository;
import com.collective.federation.entity.MemberEntity;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class MemberRepository {
    private final DataSource dataSource;
    public MemberRepository(DataSource dataSource) { this.dataSource = dataSource; }

    public void save(MemberEntity m) {
        String sql = "INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, membership_date, role, collectivity_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
            ps.setLong(12, m.getCollectivityId());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public Long getCollectivityIdByMemberId(String id) {
        String sql = "SELECT collectivity_id FROM member WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public void saveReferee(String candidateId, String refereeId, String rel) {
        String sql = "INSERT INTO member_referee (candidate_id, referee_id, relationship) VALUES (?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, candidateId); ps.setString(2, refereeId); ps.setString(3, rel);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public int countMembersByCollectivity(Long id) {
        String sql = "SELECT count(*) FROM member WHERE collectivity_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getInt(1); }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return 0;
    }
}