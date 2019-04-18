package com.example.demoflowable.form;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/form")
public class FormController {
    @Resource
    private CustomFormService customFormService;

    @GetMapping("/getFormByProcessDefId/{processDefinitionId}")
    public ResponseEntity getFormByProcessDefId(@PathVariable String processDefinitionId) {
        return ok(customFormService.getFormModelByProcessDefinitionId(processDefinitionId));
    }

    @GetMapping("/getFormByTaskId/{taskId}")
    public ResponseEntity getFormByTaskId(@PathVariable String taskId) {
        return ok(customFormService.getFormModelByTaskId(taskId));
    }
}
