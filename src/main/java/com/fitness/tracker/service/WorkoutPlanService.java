package com.fitness.tracker.service;

import com.fitness.tracker.model.WorkOutPlan;
import java.util.List;

/**
 * @author Yash
 */

public interface WorkoutPlanService {

    List<WorkOutPlan> getAllWorkoutPlans();

    WorkOutPlan getWorkoutPlanById(Long id);

    List<WorkOutPlan> getWorkoutPlansByUserId(Long userId);

    WorkOutPlan createWorkoutPlan(WorkOutPlan workoutPlan);

    WorkOutPlan updateWorkoutPlan(Long id, WorkOutPlan workoutPlanDetails);

    void deleteWorkoutPlan(Long id);
}
