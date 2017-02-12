package cn.study.jpdl_01;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

public class TransitionTest {
	@Test
	// 将流程定义发布
	public void demo1() {
		// 1. 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 3. 发布流程定义
		repositoryService.createDeployment().addResourceFromClasspath("transition.jpdl.xml").deploy();
	}

	@Test
	// 启动 transition
	public void demo2() {
		// 1. 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();
		// 3. 启动流程
		executionService.startProcessInstanceByKey("test_process");
	}

	@Test
	// 向后流转
	public void demo3() {
		// 1. 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();
		// 3. 向后流转
		executionService.signalExecutionById("test_process.50001","to task3");
	}

}
