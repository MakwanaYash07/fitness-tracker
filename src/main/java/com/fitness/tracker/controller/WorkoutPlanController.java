package com.fitness.tracker.controller;

import com.fitness.tracker.model.WorkOutPlan;
import com.fitness.tracker.service.WorkoutPlanService;
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
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    @GetMapping
    public List<WorkOutPlan> getAllWorkoutPlans() {
        return workoutPlanService.getAllWorkoutPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOutPlan> getWorkoutPlanById(@PathVariable Long id) {
        WorkOutPlan workoutPlan = workoutPlanService.getWorkoutPlanById(id);
        if (workoutPlan != null) {
            return new ResponseEntity<>(workoutPlan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public List<WorkOutPlan> getWorkoutPlansByUserId(@PathVariable Long userId) {
        return workoutPlanService.getWorkoutPlansByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> createWorkoutPlan(@Valid @RequestBody WorkOutPlan workoutPlan, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        WorkOutPlan createdWorkoutPlan = workoutPlanService.createWorkoutPlan(workoutPlan);
        return new ResponseEntity<>(createdWorkoutPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkoutPlan(@PathVariable Long id, @Valid @RequestBody WorkOutPlan workoutPlanDetails, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        WorkOutPlan updatedWorkoutPlan = workoutPlanService.updateWorkoutPlan(id, workoutPlanDetails);
        if (updatedWorkoutPlan != null) {
            return new ResponseEntity<>(updatedWorkoutPlan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        WorkOutPlan workoutPlan = workoutPlanService.getWorkoutPlanById(id);
        if (workoutPlan != null) {
            workoutPlanService.deleteWorkoutPlan(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}