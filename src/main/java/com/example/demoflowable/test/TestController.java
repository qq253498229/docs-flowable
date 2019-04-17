package com.example.demoflowable.test;

import org.flowable.engine.ManagementService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.form.api.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TestController {

    @Resource
    private FormService formService;
    @Resource
    private TaskService taskService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ManagementService managementService;
    @Resource
    private RuntimeService runtimeService;
    @GetMapping("/test")
    public ResponseEntity test(){
        System.out.println(1);
        return ok().build();
    }
}
