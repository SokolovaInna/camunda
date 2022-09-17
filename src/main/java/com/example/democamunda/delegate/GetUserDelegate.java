package com.example.democamunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GetUserDelegate implements JavaDelegate {
private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("____________");
        logger.info("execute GetUserDelegate", delegateExecution.getCurrentActivityName());
        logger.info("variable", delegateExecution.getVariable("userName"));
        logger.info("____________");
    }
}
