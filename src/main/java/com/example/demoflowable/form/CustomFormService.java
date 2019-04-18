package com.example.demoflowable.form;

import com.example.demoflowable.process.ProcessService;
import com.example.demoflowable.task.TaskService;
import org.flowable.engine.ProcessEngine;
import org.flowable.form.api.FormModel;
import org.flowable.form.engine.FormEngine;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangbin
 */
@Service
public class CustomFormService {
    @Resource
    private ProcessService processService;
    @Resource
    private TaskService taskService;
    @Resource
    private FormEngine formEngine;
    @Resource
    private ProcessEngine processEngine;

    /**
     * 通过流程定义id获取初始化表单model
     *
     * @param processDefinitionId 流程定义id
     * @return 表单model
     */
    public FormModel getFormModelByProcessDefinitionId(String processDefinitionId) {
        String startFormKey = processService.getStartFormKeyByProcessDefinitionId(processDefinitionId);
        return this.getFormModelByFormKey(startFormKey);
    }

    /**
     * 通过表单key获取表单model
     *
     * @param formKey 表单key
     * @return 表单model
     */
    public FormModel getFormModelByFormKey(String formKey) {
        return formEngine.getFormRepositoryService().getFormModelByKey(formKey).getFormModel();
    }

    public Object getFormModelByTaskId(String taskId) {
        Task task = taskService.getTaskById(taskId);
        return processEngine.getRuntimeService().getStartFormModel(task.getProcessDefinitionId(), task.getProcessInstanceId()).getFormModel();
    }

}
