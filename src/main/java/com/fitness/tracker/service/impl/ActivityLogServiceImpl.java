package com.fitness.tracker.service.impl;

import com.fitness.tracker.dao.ActivityLogDao;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.model.ActivityLog;
import com.fitness.tracker.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yash
 */

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogDao activityLogDao;

    @Override
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogDao.findAll();
    }

    @Override
    public ActivityLog getActivityLogById(Long id) {
        return activityLogDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("ActivityLog", "id", id));
    }

    @Override
    public List<ActivityLog> getActivityLogsByUserId(Long userId) {
        return activityLogDao.findByUserId(userId);
    }

    @Override
    public List<ActivityLog> getActivityLogsByWorkoutPlanId(Long workoutPlanId) {
        return activityLogDao.findByWorkoutPlanId(workoutPlanId);
    }

    @Override
    public ActivityLog createActivityLog(ActivityLog activityLog) {
        return activityLogDao.save(activityLog);
    }

    @Override
    public ActivityLog updateActivityLog(Long id, ActivityLog activityLogDetails) {
        ActivityLog activityLog = activityLogDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ActivityLog", "id", id));
        activityLog.setActivityName(activityLogDetails.getActivityName());
        activityLog.setDuration(activityLogDetails.getDuration());
        activityLog.setTimestamp(activityLogDetails.getTimestamp());
        return activityLogDao.save(activityLog);
    }

    @Override
    public void deleteActivityLog(Long id) {
        ActivityLog activityLog = activityLogDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ActivityLog", "id", id));
        activityLogDao.delete(activityLog);
    }
}