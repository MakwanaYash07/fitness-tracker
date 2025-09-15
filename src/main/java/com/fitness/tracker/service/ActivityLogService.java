package com.fitness.tracker.service;

import com.fitness.tracker.model.ActivityLog;
import java.util.List;

/**
 * @author Yash
 */

public interface ActivityLogService {

    List<ActivityLog> getAllActivityLogs();

    ActivityLog getActivityLogById(Long id);

    List<ActivityLog> getActivityLogsByUserId(Long userId);

    List<ActivityLog> getActivityLogsByWorkoutPlanId(Long workoutPlanId);

    ActivityLog createActivityLog(ActivityLog activityLog);

    ActivityLog updateActivityLog(Long id, ActivityLog activityLogDetails);

    void deleteActivityLog(Long id);
}
