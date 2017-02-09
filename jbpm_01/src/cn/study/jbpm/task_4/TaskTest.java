package cn.study.jbpm.task_4;

import java.util.List;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.junit.Test;

/**
 * 测试任务操作
 */
public class TaskTest {

	@Test
	// 查看某个流程实例的当前任务
	public void demo1(){
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		TaskService taskService = processEngine.getTaskService();
		
		// 3. 查询流程实例的任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.executionId("holiday.30001").list();
		for(Task task : list){
			System.out.println("任务编号: " + task.getId());
			System.out.println("任务名称: " + task.getName());
		}
	}
	
}
