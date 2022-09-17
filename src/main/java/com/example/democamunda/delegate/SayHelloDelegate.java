package com.example.democamunda.delegate;

import com.example.democamunda.util.GetIdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SayHelloDelegate implements JavaDelegate {
    @Autowired
private GetIdentityService getIdentityService;
private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean one =  (Math.random())< 0.5;
        delegateExecution.setVariable("One", one);
        logger.info("____________");
        logger.info("execute SayHelloDelegate " + delegateExecution.getCurrentActivityName());
        logger.info("variable: "+ delegateExecution.getVariable("One"));

        logger.info("____________");
    }
}
