package cn.study.jpdl_01;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;

/**
 * 测试分支和聚合使用
 */
public class ForkJoinTest {
	
	@Test
	// 发布流程定义
	public void demo1(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("forkjoin.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("forkjoin");
	}
	
	@Test
	// 办理我要离职任务
	public void demo2(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("8");
	}
	@Test
	// 办理人力资源
	public void demo3(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("10003");
	}
	@Test
	// 办理财务流程
	public void demo4(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("10005");
	}
	
}
