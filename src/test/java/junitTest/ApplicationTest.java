package junitTest;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.extension.mockito.CamundaMockito.autoMock;

@Deployment(resources = "Proba.bpmn")
public class ApplicationTest extends AbstractProcessEngineRuleTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Test
    public void startAndEndProcess(){
        autoMock("Proba.bpmn");
        ProcessInstance processInstance= processEngine.getRuntimeService().startProcessInstanceByKey("Proba", "013");
        assertThat(processInstance).isStarted();

    }


}
