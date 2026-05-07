package com.argiculturre.bonus.repository;

import com.argiculturre.config.DataSource;
import com.argiculturre.bonus.dto.*;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityRepositoryBonus {

    private final DataSource dataSource;

    // Constructeur explicite (injection par constructeur)
    public ActivityRepositoryBonus(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Créer une activité
    public CollectivityActivityBonus createActivity(String collectivityId, CreateCollectivityActivityBonus activity) throws SQLException {
        String sql = """
            INSERT INTO activity (id, collectivity_id, label, activity_type, 
                                  week_ordinal, day_of_week, executive_date, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, 'ACTIVE')
            """;

        String id = generateId();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.setString(2, collectivityId);
            stmt.setString(3, activity.getLabel());
            stmt.setString(4, activity.getActivityType());

            if (activity.getRecurrenceRule() != null) {
                stmt.setInt(5, activity.getRecurrenceRule().getWeekOrdinal());
                stmt.setString(6, activity.getRecurrenceRule().getDayOfWeek());
            } else {
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.setObject(7, activity.getExecutiveDate());

            stmt.executeUpdate();

            // Insérer les occupations concernées
            insertActivityOccupations(conn, id, activity.getMemberOccupationConcerned());

            CollectivityActivityBonus result = new CollectivityActivityBonus();
            result.setId(id);
            result.setLabel(activity.getLabel());
            result.setActivityType(activity.getActivityType());
            result.setMemberOccupationConcerned(activity.getMemberOccupationConcerned());
            result.setRecurrenceRule(activity.getRecurrenceRule());
            result.setExecutiveDate(activity.getExecutiveDate());

            return result;
        }
    }

    // Récupérer toutes les activités d'une collectivité
    public List<CollectivityActivityBonus> getActivitiesByCollectivityId(String collectivityId) throws SQLException {
        String sql = """
            SELECT a.id, a.label, a.activity_type, a.week_ordinal, a.day_of_week, a.executive_date
            FROM activity a
            WHERE a.collectivity_id = ? AND a.status = 'ACTIVE'
            """;

        List<CollectivityActivityBonus> activities = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CollectivityActivityBonus activity = new CollectivityActivityBonus();
                activity.setId(rs.getString("id"));
                activity.setLabel(rs.getString("label"));
                activity.setActivityType(rs.getString("activity_type"));

                // Récupérer les occupations pour cette activité
                List<String> occupations = getActivityOccupations(conn, activity.getId());
                activity.setMemberOccupationConcerned(occupations);

                // Reconstruire recurrence rule si existe
                int weekOrdinal = rs.getInt("week_ordinal");
                String dayOfWeek = rs.getString("day_of_week");
                if (!rs.wasNull() && dayOfWeek != null) {
                    activity.setRecurrenceRule(new MonthlyRecurrenceRuleBonus(weekOrdinal, dayOfWeek));
                }

                LocalDate execDate = rs.getDate("executive_date") != null ?
                        rs.getDate("executive_date").toLocalDate() : null;
                activity.setExecutiveDate(execDate);

                activities.add(activity);
            }
        }
        return activities;
    }

    // Récupérer les occupations d'une activité
    private List<String> getActivityOccupations(Connection conn, String activityId) throws SQLException {
        String sql = "SELECT occupation FROM activity_occupation WHERE activity_id = ?";
        List<String> occupations = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                occupations.add(rs.getString("occupation"));
            }
        }
        return occupations;
    }

    // Enregistrer la présence des membres
    public void saveAttendance(String collectivityId, String activityId,
                               List<CreateActivityMemberAttendanceBonus> attendances) throws SQLException {

        // Vérifier si l'activité existe
        if (!activityExists(collectivityId, activityId)) {
            throw new SQLException("Activity not found");
        }

        String sql = """
            INSERT INTO attendance (id, activity_id, member_id, attendance_status, created_at)
            VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
            ON CONFLICT (activity_id, member_id) DO NOTHING
            """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (CreateActivityMemberAttendanceBonus attendance : attendances) {
                // Vérifier si déjà confirmé (pas de modification)
                if (isAttendanceConfirmed(conn, activityId, attendance.getMemberIdentifier())) {
                    throw new SQLException("Attendance already confirmed for member: " +
                            attendance.getMemberIdentifier());
                }

                stmt.setString(1, generateId());
                stmt.setString(2, activityId);
                stmt.setString(3, attendance.getMemberIdentifier());
                stmt.setString(4, attendance.getAttendanceStatus());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Récupérer la liste des présences
    public List<ActivityMemberAttendanceBonus> getAttendance(String collectivityId, String activityId) throws SQLException {
        String sql = """
            SELECT a.id, a.attendance_status, 
                   m.id as member_id, m.first_name, m.last_name, m.email,
                   ms.occupation
            FROM attendance a
            JOIN member m ON a.member_id = m.id
            LEFT JOIN membership ms ON ms.member_id = m.id AND ms.collectivity_id = ?
            WHERE a.activity_id = ?
            """;

        List<ActivityMemberAttendanceBonus> attendances = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, collectivityId);
            stmt.setString(2, activityId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MemberDescriptionBonus memberDesc = new MemberDescriptionBonus(
                        rs.getString("member_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("occupation")
                );

                ActivityMemberAttendanceBonus attendance = new ActivityMemberAttendanceBonus(
                        rs.getString("id"),
                        memberDesc,
                        rs.getString("attendance_status")
                );
                attendances.add(attendance);
            }
        }
        return attendances;
    }

    // Helper: générer ID
    private String generateId() {
        return "act-" + System.currentTimeMillis() + "-" +
                java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    // Helper: insérer les occupations
    private void insertActivityOccupations(Connection conn, String activityId, List<String> occupations)
            throws SQLException {
        if (occupations == null || occupations.isEmpty()) return;

        String sql = "INSERT INTO activity_occupation (activity_id, occupation) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (String occupation : occupations) {
                stmt.setString(1, activityId);
                stmt.setString(2, occupation);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Helper: vérifier si activité existe
    private boolean activityExists(String collectivityId, String activityId) throws SQLException {
        String sql = "SELECT 1 FROM activity WHERE id = ? AND collectivity_id = ? AND status = 'ACTIVE'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activityId);
            stmt.setString(2, collectivityId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Helper: vérifier si présence déjà confirmée (non modifiable)
    private boolean isAttendanceConfirmed(Connection conn, String activityId, String memberId)
            throws SQLException {
        String sql = "SELECT attendance_status FROM attendance WHERE activity_id = ? AND member_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activityId);
            stmt.setString(2, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String status = rs.getString("attendance_status");
                // UNDEFINED peut être modifié
                return !"UNDEFINED".equals(status);
            }
            return false;
        }
    }
}