package cn.study.jpdl_01;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;

/**
 * 测试状态节点（等待节点）的使用
 */
public class StateTest {
	
	@Test
	// 发布流程定义，启动流程实例
	public void demo1(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("state.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("state");
	}
	
	@Test
	// 办理任务
	public void demo2(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("8");
	}
	@Test
	// 收到支付宝回应
	public void demo3(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getExecutionService().signalExecutionById("state.7");
	}

}
