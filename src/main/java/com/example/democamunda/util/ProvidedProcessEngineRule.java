package com.example.democamunda.util;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.test.ProcessEngineRule;

import java.util.concurrent.Callable;

public class ProvidedProcessEngineRule extends ProcessEngineRule {
    protected static ProcessEngine cachedProcessEngine;
    protected Callable<ProcessEngine> processEngineCallable;

    public ProvidedProcessEngineRule() {
        super(getOrInitializeProcess(), true);
    }

    public ProvidedProcessEngineRule(final ProcessEngineBootstrapRule bootstrapRule) {
        this(bootstrapRule::getProcessEngine);
    }


    public ProvidedProcessEngineRule(Callable<ProcessEngine> processEngineCallable) {
        super(true);
        this.processEngineCallable = processEngineCallable;
    }

    @Override
    protected void initializeProcessEngine() {
        if (processEngineCallable != null) {
            try {
                this.processEngine = processEngineCallable.call();
            } catch (Exception e) {
                throw new RuntimeException("Could not get process engine", e);
            }

        } else {
            super.initializeProcessEngine();
        }
    }

    protected static ProcessEngine getOrInitializeProcess() {
        if (cachedProcessEngine == null) {
            cachedProcessEngine = ProcessEngineConfiguration
                    .createProcessEngineConfigurationFromResource("camunda.cfg.xml")
                    .buildProcessEngine();
        }
        return cachedProcessEngine;
    }
}
