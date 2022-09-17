package com.example.democamunda.controller;

import com.example.democamunda.dto.ProcessInstanceInfo;
import com.example.democamunda.util.GetCurrentProcessEngine;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/starttask")
public class StartTaskController {
    private final GetCurrentProcessEngine currentProcessEngine;
    private final TaskService taskService;

    public StartTaskController(GetCurrentProcessEngine currentProcessEngine, TaskService taskService) {
        this.currentProcessEngine = currentProcessEngine;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<String> comleteTask (@RequestBody @Valid ProcessInstanceInfo processInstanceInfo){
        ProcessEngine processEngine= currentProcessEngine.getDefaultProcessEngine();
        Task task=processEngine.getTaskService()
                .createTaskQuery()
                .active()
                .processInstanceId(processInstanceInfo.getProcessInstanceId())
                .singleResult();
        taskService.complete(task.getId());
        return ResponseEntity.ok(task.getId()+"task complete");
    }
}
