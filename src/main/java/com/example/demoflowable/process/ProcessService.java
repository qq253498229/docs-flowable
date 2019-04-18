package com.example.demoflowable.process;

import com.example.demoflowable.task.TaskService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wangbin
 */
@Service
public class ProcessService {
    /**
     * 当前app，实际项目需要存储在数据库或其它位置(比如consul)
     */
    public static final String APP_KEY = "test_app";

    @Resource
    private ProcessEngine processEngine;
    @Resource
    private TaskService taskService;

    public ProcessDefinition getProcessDefinitionById(String processDefinitionId) {
        return processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    }

    /**
     * 通过流程定义id获取初始化表单key
     *
     * @param processDefinitionId 流程定义id
     * @return 初始化表单key
     */
    public String getStartFormKeyByProcessDefinitionId(String processDefinitionId) {
        return processEngine.getFormService().getStartFormKey(processDefinitionId);
    }

    /**
     * 获取流程定义列表
     *
     * @return 流程定义列表
     */
    public List<ProcessDefinition> getAll() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment currentApp = repositoryService.createDeploymentQuery().deploymentKey(APP_KEY).latest().singleResult();
        return repositoryService.createProcessDefinitionQuery().deploymentId(currentApp.getId()).latestVersion().list();
    }

    /**
     * 通过流程定义获取参数
     *
     * @param processInstanceId 流程定义id
     * @return 参数
     */
    public Map<String, Object> getProcessVariablesByProcessInstanceId(String processInstanceId) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        return runtimeService.getVariables(processInstanceId);
    }

    /**
     * 通过流程定义和参数启动流程实例
     *
     * @param processDefinitionId 流程定义id
     * @param variables           参数
     */
    public void startProcessByProcessDefinitionIdAndVariables(String processDefinitionId, Map<String, Object> variables) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceWithForm(processDefinitionId, null, variables, null);
    }

    /**
     * 通过任务id获取流程参数
     *
     * @param taskId 任务id
     * @return 流程参数
     */
    public Map<String, Object> getProcessVariablesByTaskId(String taskId) {
        Task taskById = taskService.getTaskById(taskId);
        return this.getProcessVariablesByProcessInstanceId(taskById.getProcessInstanceId());
    }
}
