package cn.study.jpdl_01;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

public class ProcessTest {
	@Test
	// 将流程定义发布
	public void demo1(){
		// 1. 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		// 3. 发布流程定义
		repositoryService.createDeployment().addResourceFromClasspath("process.jpdl.xml").deploy();
	}

}
