package com.example.demoflowable.task;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private ProcessEngine processEngine;

    /**
     * 查看自己的任务
     *
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Object>> list(@PathVariable String userId) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        List<Object> collect = list.stream().map(s -> {
            Map<String, Object> persistentState = (HashMap) ((TaskEntityImpl) s).getPersistentState();
            persistentState.put("id", s.getId());
            return persistentState;
        }).collect(Collectors.toList());
        return ok(collect);
    }

    /**
     * 领取任务
     *
     * @return
     */
    @PostMapping("/claim/{userId}/{taskId}")
    public ResponseEntity claim(@PathVariable String userId, @PathVariable String taskId) {
        TaskService taskService = processEngine.getTaskService();
        taskService.claim(taskId, userId);
        return ok().build();
    }

    @GetMapping("/taskInfo/{taskId}")
    public ResponseEntity taskInfo(@PathVariable String taskId) {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> taskMap = (HashMap) ((TaskEntityImpl) task).getPersistentState();
        taskMap.put("id", task.getId());

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        return ok(taskMap);
    }
}
