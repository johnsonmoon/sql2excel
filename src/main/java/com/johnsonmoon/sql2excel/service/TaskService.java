package com.johnsonmoon.sql2excel.service;

import com.johnsonmoon.sql2excel.entity.Task;
import com.johnsonmoon.sql2excel.entity.param.TaskCreateParam;

/**
 * Create by xuyh at 2019/5/11 15:43.
 */
public interface TaskService {
    /**
     * Create a task
     *
     * @param param {@link TaskCreateParam}
     * @return {@link Task}
     */
    Task create(TaskCreateParam param);

    /**
     * Stop a task
     *
     * @param taskId {@link Task#taskId}
     * @return {@link Task}
     */
    Task stop(String taskId);

    /**
     * Pause a task
     *
     * @param taskId {@link Task#taskId}
     * @return {@link Task}
     */
    Task pause(String taskId);

    /**
     * Continue a task
     *
     * @param taskId {@link Task#taskId}
     * @return {@link Task}
     */
    Task continueRun(String taskId);

    /**
     * Get a task status
     *
     * @param taskId {@link Task#taskId}
     * @return {@link Task}
     */
    Task status(String taskId);
}
