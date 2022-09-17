package com.example.democamunda.util;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.runtime.Job;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.List;
import java.util.function.Consumer;

public class ProcessEngineBootstrapRule extends TestWatcher {
    private ProcessEngine processEngine;
    protected Consumer<ProcessEngineConfigurationImpl> processEngineConfiguration;


    public ProcessEngineBootstrapRule(String congigur, Consumer<ProcessEngineConfigurationImpl> processEngineConfiguration) {
        this.processEngine = bootstapEngine(congigur);
        this.processEngineConfiguration = processEngineConfiguration;
    }

    public ProcessEngineBootstrapRule(Consumer<ProcessEngineConfigurationImpl> processEngineConfiguration) {
        this("camunda.cfg.xml", processEngineConfiguration);
    }

    public ProcessEngineBootstrapRule() {
        this("camunda.cfg.xml");
    }

    public ProcessEngineBootstrapRule(String configur) {
        this(configur, null);
    }

    public ProcessEngine bootstapEngine(String configur) {
        ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource(configur);
        configureEngine(processEngineConfiguration);
        return processEngineConfiguration.buildProcessEngine();
    }

    public ProcessEngineConfiguration configureEngine(ProcessEngineConfigurationImpl configuration) {
        if (processEngineConfiguration != null) {
            processEngineConfiguration.accept(configuration);
        }
        return configuration;
    }

    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    @Override
    protected void finished(Description description) {
        deleteHistoryCleanupJob();
        processEngine.close();
        ProcessEngines.unregister(processEngine);
        processEngine = null;
    }

    private void deleteHistoryCleanupJob() {
        final List<Job> jobList = processEngine.getHistoryService().findHistoryCleanupJobs();
        for (final Job job : jobList) {
            ((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration()).
                    getCommandExecutorTxRequired().execute(new Command<Void>() {

                        public Void execute(CommandContext commandContext) {
                            commandContext.getJobManager().deleteJob((JobEntity) job);
                            commandContext.getHistoricJobLogManager().deleteHistoricJobLogByJobId(job.getId());
                            return null;

                        }
                    });
        }

    }

}
