package com.example.demoflowable.form;

import org.flowable.engine.ProcessEngine;
import org.flowable.form.api.FormModel;
import org.flowable.form.engine.FormEngine;
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
    private FormEngine formEngine;
    @Resource
    private ProcessEngine processEngine;

    @GetMapping("/getFormByProcessDefId/{processDefinitionId}")
    public ResponseEntity get(@PathVariable String processDefinitionId) {
        String startFormKey = processEngine.getFormService().getStartFormKey(processDefinitionId);
        FormModel formModel = formEngine.getFormRepositoryService().getFormModelByKey(startFormKey).getFormModel();
        return ok(formModel);
    }
}
