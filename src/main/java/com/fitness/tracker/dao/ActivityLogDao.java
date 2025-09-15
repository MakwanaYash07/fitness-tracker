package com.fitness.tracker.dao;

import com.fitness.tracker.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yash
 */

@Repository
public interface ActivityLogDao extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByUserId(Long userId);
    List<ActivityLog> findByWorkoutPlanId(Long workoutPlanId);
}
