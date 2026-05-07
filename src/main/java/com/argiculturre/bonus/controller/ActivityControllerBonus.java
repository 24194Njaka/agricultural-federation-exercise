package com.argiculturre.bonus.controller;

import com.argiculturre.bonus.dto.*;
import com.argiculturre.bonus.service.ActivityServiceBonus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor


public class ActivityControllerBonus {
    private final ActivityServiceBonus activityService;

    @PostMapping("/{id}/activities")
    public ResponseEntity<List<CollectivityActivityBonus>> createActivities(
            @PathVariable("id") String collectivityId,
            @RequestBody List<CreateCollectivityActivityBonus> activities) {

        List<CollectivityActivityBonus> created = activityService.createActivities(collectivityId, activities);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<CollectivityActivityBonus>> getActivities(
            @PathVariable("id") String collectivityId) {

        List<CollectivityActivityBonus> activities = activityService.getActivities(collectivityId);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/{id}/activities/{activityId}/attendance")
    public ResponseEntity<List<ActivityMemberAttendanceBonus>> markAttendance(
            @PathVariable("id") String collectivityId,
            @PathVariable("activityId") String activityId,
            @RequestBody List<CreateActivityMemberAttendanceBonus> attendances) {

        List<ActivityMemberAttendanceBonus> result = activityService.markAttendance(collectivityId, activityId, attendances);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}/activities/{activityId}/attendance")
    public ResponseEntity<List<ActivityMemberAttendanceBonus>> getAttendance(
            @PathVariable("id") String collectivityId,
            @PathVariable("activityId") String activityId) {

        List<ActivityMemberAttendanceBonus> attendances = activityService.getAttendance(collectivityId, activityId);
        return ResponseEntity.ok(attendances);
    }

}
