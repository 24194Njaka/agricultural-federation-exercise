package com.collective.federation.repository;

import com.collective.federation.entity.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbc;

    public MemberRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Member member) {
        String sql = "INSERT INTO member (id, first_name, last_name, birth_date, gender, address, " +
                "profession, phone_number, email, occupation, collectivity_id, " +
                "registration_fee_paid, membership_dues_paid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbc.update(sql,
                member.getId(), member.getFirstName(), member.getLastName(), member.getBirthDate(),
                member.getGender(), member.getAddress(), member.getProfession(), member.getPhoneNumber(),
                member.getEmail(), member.getOccupation(), member.getCollectivityId(),
                member.isRegistrationFeePaid(), // 50,000 MGA [cite: 89]
                member.isMembershipDuesPaid()   // Cotisations annuelles [cite: 89]
        );
    }

    public void saveReferee(String candidateId, String refereeId, String relationship) {
        String sql = "INSERT INTO member_referee (candidate_id, referee_id, relationship) VALUES (?, ?, ?)";
        jdbc.update(sql, candidateId, refereeId, relationship);
    }

    public String getCollectivityIdByMemberId(String memberId) {
        String sql = "SELECT collectivity_id FROM member WHERE id = ?";
        return jdbc.queryForObject(sql, String.class, memberId);
    }

    public int countMembersByCollectivity(String collectivityId) {
        String sql = "SELECT count(*) FROM member WHERE collectivity_id = ?";
        return jdbc.queryForObject(sql, Integer.class, collectivityId);
    }
}
