package cn.study.jbpm.variables_05;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jbpm.api.Configuration;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskService;
import org.junit.Test;

/**
 * 测试流程变量读写
 */
public class VariablesTest {

	@Test
	// 启动流程时，关联变量
	public void demo1() {
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();
		// 3. 启动流程实例
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("company", "computer");
		executionService.startProcessInstanceByKey("holiday", variables);

	}

	@Test
	// 使用 ExecutionService 读写流程变量
	public void demo2() {
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();

		// 读写流程变量
		String company = (String) executionService.getVariable("holiday.50001", "company");
		System.out.println("company:  " + company);

		executionService.setVariable("holiday.50001", "pnum", 100);

	}

	@Test
	// 使用 ExecutionService 读写流程变量
	public void demo3() {
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		TaskService taskService = processEngine.getTaskService();
		// 读取变量
		int pnum = (int) taskService.getVariable("50003", "pnum");
		System.out.println("pnum :    " + pnum);

		// 写入变量
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("weather", "多云无雨");
		taskService.setVariables("50003", variables);
	}

	@Test
	// 测试保存可序列化的对象
	public void demo4() {
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();

		// 保存 User 对象
		User user = new User();
		user.setId(1001);
		user.setName("张三");

		executionService.setVariable("holiday.50001", "user", user);

	}

	@Test
	// 读取保存后的序列化对象
	public void demo5() {
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();
		// 读取 User 对象
		User user = (User) executionService.getVariable("holiday.50001", "user");
		System.out.println("user:  " + user.toString());

	}

	@Test
	// 测试保存持久化 PO 对象
	public void demo6() {
		// 1. 流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2. 获得对应 Service
		ExecutionService executionService = processEngine.getExecutionService();
		Session session = new org.hibernate.cfg.Configuration().configure("jbpm.hibernate.cfg.xml")
				.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Book book = new Book();
		book.setBookName("《JBPM入门指南》");
		session.save(book);
		tx.commit();

		executionService.setVariable("holiday.50001", "book", book);

		session.clear();

	}

}
