package com.fitness.tracker.dao;

import com.fitness.tracker.model.WorkOutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yash
 */

@Repository
public interface WorkOutPlanDao extends JpaRepository<WorkOutPlan, Long> {
    List<WorkOutPlan> findByUserId(Long userId);
}
