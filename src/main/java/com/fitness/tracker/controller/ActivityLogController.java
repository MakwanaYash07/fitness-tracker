package com.fitness.tracker.controller;

import com.fitness.tracker.model.ActivityLog;
import com.fitness.tracker.service.ActivityLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Yash
 */

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogService.getAllActivityLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable Long id) {
        ActivityLog activityLog = activityLogService.getActivityLogById(id);
        if (activityLog != null) {
            return new ResponseEntity<>(activityLog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public List<ActivityLog> getActivityLogsByUserId(@PathVariable Long userId) {
        return activityLogService.getActivityLogsByUserId(userId);
    }

    @GetMapping("/workout-plan/{workoutPlanId}")
    public List<ActivityLog> getActivityLogsByWorkoutPlanId(@PathVariable Long workoutPlanId) {
        return activityLogService.getActivityLogsByWorkoutPlanId(workoutPlanId);
    }

    @PostMapping
    public ResponseEntity<?> createActivityLog(@Valid @RequestBody ActivityLog activityLog, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        ActivityLog createdActivityLog = activityLogService.createActivityLog(activityLog);
        return new ResponseEntity<>(createdActivityLog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivityLog(@PathVariable Long id, @Valid @RequestBody ActivityLog activityLogDetails, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        ActivityLog updatedActivityLog = activityLogService.updateActivityLog(id, activityLogDetails);
        if (updatedActivityLog != null) {
            return new ResponseEntity<>(updatedActivityLog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityLog(@PathVariable Long id) {
        ActivityLog activityLog = activityLogService.getActivityLogById(id);
        if (activityLog != null) {
            activityLogService.deleteActivityLog(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
