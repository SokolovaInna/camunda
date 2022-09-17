package com.example.democamunda.util;

import com.example.democamunda.dto.ProcessInstanceInfo;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class StartProcess {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    private final RuntimeService runtimeService;
    private final ProcessInstanceInfo processInstanceInfo;

    public StartProcess(RuntimeService runtimeService, ProcessInstanceInfo processInstanceInfo) {
        this.runtimeService = runtimeService;
        this.processInstanceInfo = processInstanceInfo;
    }

    public ProcessInstanceInfo placeStart(String nameProcess, Map<String, Object> vars){
        ProcessInstance processInstance;
        if(vars.isEmpty()){
            processInstance=runtimeService.startProcessInstanceByKey(nameProcess, UUID.randomUUID().toString(), vars);
        }
        else {
            processInstance=runtimeService.startProcessInstanceByKey(nameProcess, UUID.randomUUID().toString());
        }
        processInstanceInfo.setBusinessKey(processInstance.getBusinessKey());
        processInstanceInfo.setProcessInstanceId(processInstance.getProcessInstanceId());
        logger.info("Процесс {} запущен, BusinessKey {},ProcessInstanceId {} ", nameProcess, processInstance.getBusinessKey(),processInstance.getProcessInstanceId());
        return processInstanceInfo;

    }
    public ProcessInstanceInfo placeStart(String nameProcess){
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(nameProcess, UUID.randomUUID().toString());
        processInstanceInfo.setBusinessKey(processInstance.getBusinessKey());
        processInstanceInfo.setProcessInstanceId(processInstance.getProcessInstanceId());
        logger.info("Процесс {} запущен, BusinessKey {},ProcessInstanceId {} ", nameProcess, processInstance.getBusinessKey(),processInstance.getProcessInstanceId());
        return processInstanceInfo;

    }

}
