package com.example.demoflowable.process;


import lombok.Data;
import org.flowable.engine.repository.ProcessDefinition;

/**
 * @author wangbin
 */
@Data
public class ProcessDefinitionDTO {

    private String id;
    private String key;
    private String name;
    private String deploymentId;
    private Integer version;

    public ProcessDefinitionDTO fromDefinition(ProcessDefinition definition) {
        this.id = definition.getId();
        this.key = definition.getKey();
        this.name = definition.getName();
        this.deploymentId = definition.getDeploymentId();
        this.version = definition.getVersion();
        return this;
    }
}
