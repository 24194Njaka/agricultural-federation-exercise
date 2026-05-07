package com.argiculturre.bonus.service;

import com.argiculturre.bonus.dto.*;
import com.argiculturre.bonus.repository.ActivityRepositoryBonus;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceBonus {

    private final ActivityRepositoryBonus activityRepository;

    // Constructeur explicite
    public ActivityServiceBonus(ActivityRepositoryBonus activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<CollectivityActivityBonus> createActivities(String collectivityId,
                                                            List<CreateCollectivityActivityBonus> activities) {
        try {
            List<CollectivityActivityBonus> result = new ArrayList<>();
            for (CreateCollectivityActivityBonus activity : activities) {
                // Validation: soit recurrenceRule, soit executiveDate, pas les deux
                if (activity.getRecurrenceRule() != null && activity.getExecutiveDate() != null) {
                    throw new IllegalArgumentException("Cannot provide both recurrenceRule and executiveDate");
                }
                // Validation: au moins l'un des deux
                if (activity.getRecurrenceRule() == null && activity.getExecutiveDate() == null) {
                    throw new IllegalArgumentException("Must provide either recurrenceRule or executiveDate");
                }

                CollectivityActivityBonus created = activityRepository.createActivity(collectivityId, activity);
                result.add(created);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating activities: " + e.getMessage(), e);
        }
    }

    public List<CollectivityActivityBonus> getActivities(String collectivityId) {
        try {
            return activityRepository.getActivitiesByCollectivityId(collectivityId);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching activities: " + e.getMessage(), e);
        }
    }

    public List<ActivityMemberAttendanceBonus> markAttendance(String collectivityId, String activityId,
                                                              List<CreateActivityMemberAttendanceBonus> attendances) {
        try {
            activityRepository.saveAttendance(collectivityId, activityId, attendances);
            return getAttendance(collectivityId, activityId);
        } catch (SQLException e) {
            throw new RuntimeException("Error marking attendance: " + e.getMessage(), e);
        }
    }

    public List<ActivityMemberAttendanceBonus> getAttendance(String collectivityId, String activityId) {
        try {
            return activityRepository.getAttendance(collectivityId, activityId);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching attendance: " + e.getMessage(), e);
        }
    }
}