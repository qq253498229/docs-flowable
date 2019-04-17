package com.example.demoflowable.task;

import lombok.Data;

/**
 * @author wangbin
 */
@Data
public class TaskDTO {
    private String id;
    private String name;
    private String processInstanceId;
    private String processDefinitionId;

}
