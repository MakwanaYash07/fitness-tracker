package com.fitness.tracker.service;

import com.fitness.tracker.dao.WorkOutPlanDao;
import com.fitness.tracker.model.User;
import com.fitness.tracker.model.WorkOutPlan;
import com.fitness.tracker.service.impl.WorkoutPlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Yash
 */

class WorkOutPlanServiceTest {

    @Mock
    private WorkOutPlanDao workOutPlanDao;

    @InjectMocks
    private WorkoutPlanServiceImpl workOutPlanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPlan_success() {
        WorkOutPlan plan = new WorkOutPlan();
        plan.setId(1L);
        plan.setName("Summer Shred");
        plan.setStartDate(LocalDate.now());
        plan.setEndDate(LocalDate.now().plusDays(30));
        plan.setUser(new User());

        when(workOutPlanDao.save(any(WorkOutPlan.class))).thenReturn(plan);

        WorkOutPlan saved = workOutPlanService.createWorkoutPlan(plan);

        assertNotNull(saved);
        assertEquals("Summer Shred", saved.getName());
    }

    @Test
    void getPlanById_found() {
        WorkOutPlan plan = new WorkOutPlan();
        plan.setId(10L);
        plan.setName("Bulk Plan");

        when(workOutPlanDao.findById(10L)).thenReturn(Optional.of(plan));

        WorkOutPlan result = workOutPlanService.getWorkoutPlanById(10L);

        assertEquals("Bulk Plan", result.getName());
    }

    @Test
    void getPlanById_notFound() {
        when(workOutPlanDao.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> workOutPlanService.getWorkoutPlanById(99L));
    }
}


