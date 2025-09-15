package com.fitness.tracker.service.impl;

import com.fitness.tracker.dao.WorkOutPlanDao;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.model.WorkOutPlan;
import com.fitness.tracker.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yash
 */

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    @Autowired
    private WorkOutPlanDao workOutPlanDao;

    @Override
    public List<WorkOutPlan> getAllWorkoutPlans() {
        return workOutPlanDao.findAll();
    }

    @Override
    public WorkOutPlan getWorkoutPlanById(Long id) {
        return workOutPlanDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("WorkoutPlan", "id", id));
    }

    @Override
    public List<WorkOutPlan> getWorkoutPlansByUserId(Long userId) {
        return workOutPlanDao.findByUserId(userId);
    }

    @Override
    public WorkOutPlan createWorkoutPlan(WorkOutPlan workoutPlan) {
        return workOutPlanDao.save(workoutPlan);
    }

    @Override
    public WorkOutPlan updateWorkoutPlan(Long id, WorkOutPlan workoutPlanDetails) {
        WorkOutPlan workoutPlan = workOutPlanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutPlan", "id", id));

        workoutPlan.setName(workoutPlanDetails.getName());
        workoutPlan.setStartDate(workoutPlanDetails.getStartDate());
        workoutPlan.setEndDate(workoutPlanDetails.getEndDate());
        return workOutPlanDao.save(workoutPlan);
    }

    @Override
    public void deleteWorkoutPlan(Long id) {
        WorkOutPlan workoutPlan = workOutPlanDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutPlan", "id", id));
        workOutPlanDao.delete(workoutPlan);
    }
}
