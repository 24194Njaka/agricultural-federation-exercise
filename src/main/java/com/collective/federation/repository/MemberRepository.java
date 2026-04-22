package com.collective.federation.repository;

import com.collective.federation.entity.Member;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class MemberRepository {

    private final DataSource dataSource;

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Member member) {
        String sql = "INSERT INTO member (id, first_name, last_name, occupation, collectivity_id, registration_fee_paid, membership_dues_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getId());
            stmt.setString(2, member.getFirstName());
            stmt.setString(3, member.getLastName());
            stmt.setString(4, member.getOccupation());
            stmt.setString(5, member.getCollectivityId());
            stmt.setBoolean(6, member.isRegistrationFeePaid());
            stmt.setBoolean(7, member.isMembershipDuesPaid());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error during member save", e);
        }
    }

    public String getCollectivityIdByMemberId(String memberId) {
        String sql = "SELECT collectivity_id FROM member WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getString("collectivity_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void saveReferee(String candidateId, String refereeId, String relationship) {
        String sql = "INSERT INTO member_referee (candidate_id, referee_id, relationship) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, candidateId);
            stmt.setString(2, refereeId);
            stmt.setString(3, relationship);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countMembersByCollectivity(String collectivityId) {
        String sql = "SELECT count(*) FROM member WHERE collectivity_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}