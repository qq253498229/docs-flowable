package com.example.demoflowable.task;

import org.flowable.engine.ProcessEngine;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangbin
 */
@Service
public class TaskService {
    @Resource
    private ProcessEngine processEngine;

    public List<Task> getTaskListByUserId(String userId) {
        org.flowable.engine.TaskService taskService = processEngine.getTaskService();
        return taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
    }

    public Task getTaskById(String taskId) {
        return processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    }

    public void claim(String taskId, String userId) {
        org.flowable.engine.TaskService taskService = processEngine.getTaskService();
        taskService.claim(taskId, userId);
    }
}
