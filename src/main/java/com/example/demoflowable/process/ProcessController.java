package com.example.demoflowable.process;

import org.flowable.engine.repository.ProcessDefinition;
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
    @Resource
    private ProcessService processService;

    /**
     * 获取当前app内的流程列表
     */
    @GetMapping
    public ResponseEntity<List<ProcessDefinitionDTO>> list() {
        List<ProcessDefinition> list = processService.getAll();
        List<ProcessDefinitionDTO> result = list.stream().map(s -> new ProcessDefinitionDTO().fromDefinition(s)).collect(Collectors.toList());
        return ok(result);
    }

    /**
     * 获取流程参数
     */
    @GetMapping("/variables/{processInstanceId}")
    public ResponseEntity<Map<String, Object>> variables(@PathVariable String processInstanceId) {
        Map<String, Object> variables = processService.getProcessVariablesByProcessInstanceId(processInstanceId);
        return ok(variables);
    }

    /**
     * 通过参数启动流程
     */
    @PostMapping("/{processDefinitionId}")
    public ResponseEntity start(@PathVariable String processDefinitionId, @RequestBody Map<String, Object> variables) {
        processService.startProcessByProcessDefinitionIdAndVariables(processDefinitionId, variables);
        return ok().build();
    }
}
