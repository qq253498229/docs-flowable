package com.example.demoflowable.process;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.engine.FormEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/process")
public class ProcessController {
    public static final String APP_KEY = "test_app";
    @Resource
    private ProcessEngine processEngine;

    /**
     * 获取当前app内的流程列表
     */
    @GetMapping
    public ResponseEntity list() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment currentApp = repositoryService.createDeploymentQuery().deploymentKey(APP_KEY).latest().singleResult();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(currentApp.getId()).latestVersion().list();
        List<ProcessDefinitionDTO> result = list.stream().map(s -> new ProcessDefinitionDTO().fromDefinition(s)).collect(Collectors.toList());
        return ok(result);
    }

    @GetMapping("/variables/{processInstanceId}")
    public ResponseEntity getProcessVariables(@PathVariable String processInstanceId) {
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        Map<String, Object> processVariables = processInstance.getProcessVariables();
        return ok(processVariables);
    }

    /**
     * 启动流程
     */
    @PostMapping("/{processDefinitionId}")
    public ResponseEntity start(@PathVariable String processDefinitionId, @RequestBody Map<String, Object> variables) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceById(processDefinitionId, variables);
        return ok().build();
    }
}
