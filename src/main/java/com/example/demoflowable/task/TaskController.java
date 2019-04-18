package com.example.demoflowable.task;

import com.example.demoflowable.process.ProcessService;
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
    private TaskService taskService;

    @Resource
    private ProcessService processService;

    /**
     * 查看自己的任务
     *
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Object>> list(@PathVariable String userId) {
        List<Task> list = taskService.getTaskListByUserId(userId);
        List<Object> collect = list.stream().map(s -> {
            Map<String, Object> persistentState = (HashMap) ((TaskEntityImpl) s).getPersistentState();
            persistentState.put("id", s.getId());
            return persistentState;
        }).collect(Collectors.toList());
        return ok(collect);
    }

    /**
     * 获取流程参数
     */
    @GetMapping("/variables/{taskId}")
    public ResponseEntity variables(@PathVariable String taskId) {
        Map<String, Object> variables = processService.getProcessVariablesByTaskId(taskId);
        return ok(variables);
    }

    /**
     * 领取任务
     */
    @PostMapping("/claim/{userId}/{taskId}")
    public ResponseEntity claim(@PathVariable String userId, @PathVariable String taskId) {
        taskService.claim(taskId, userId);
        return ok().build();
    }
}
