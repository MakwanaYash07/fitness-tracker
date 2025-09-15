package com.fitness.tracker.service;

import com.fitness.tracker.dao.ActivityLogDao;
import com.fitness.tracker.model.ActivityLog;
import com.fitness.tracker.service.impl.ActivityLogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Yash
 */

class ActivityLogServiceTest {

    @Mock
    private ActivityLogDao activityLogDao;

    @InjectMocks
    private ActivityLogServiceImpl activityLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createActivity_success() {
        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActivityName("Running");
        log.setDuration(30);

        when(activityLogDao.save(any(ActivityLog.class))).thenReturn(log);

        ActivityLog saved = activityLogService.createActivityLog(log);

        assertNotNull(saved);
        assertEquals("Running", saved.getActivityName());
    }

    @Test
    void getActivityById_found() {
        ActivityLog log = new ActivityLog();
        log.setId(5L);
        log.setActivityName(("Cycling"));

        when(activityLogDao.findById(5L)).thenReturn(Optional.of(log));

        ActivityLog result = activityLogService.getActivityLogById(5L);

        assertEquals("Cycling", result.getActivityName());
    }

    @Test
    void getActivityById_notFound() {
        when(activityLogDao.findById(50L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> activityLogService.getActivityLogById(50L));
    }
}

