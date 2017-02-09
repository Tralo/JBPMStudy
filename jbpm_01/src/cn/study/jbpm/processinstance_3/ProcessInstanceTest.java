package cn.study.jbpm.processinstance_3;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Test;

public class ProcessInstanceTest {

	@Test
	// 启动流程实例
	public void demo1() {
		// 步骤一: 获取流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应 service 对象
		ExecutionService executionService = processEngine.getExecutionService();

		// 步骤三: 启动流程实例
		// executionService.startProcessInstanceById("holiday-1");//ID 是唯一的

		// 根据 pdKey 启动（jbpm4_deployprop存在key属性）
		executionService.startProcessInstanceByKey("holiday");// key 可以相同的

	}

	@Test
	// 测试流程实例 向后流转
	public void demo2() {
		// 步骤一: 获取流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应 service 对象
		ExecutionService executionService = processEngine.getExecutionService();
		// 步骤三: 根据实例id，向后流转
		// executionService.signalExecutionById("holiday.50001","to 部门经理审批");
		executionService.signalExecutionById("holiday.50001", "to end1");
	}

	@Test
	public void demo3() {
		// 步骤一: 获取流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应 service 对象
		ExecutionService executionService = processEngine.getExecutionService();
		// 步骤三: 直接中止流程
		executionService.endProcessInstance("holiday.60001", ProcessInstance.STATE_ENDED);
		
	}

}
