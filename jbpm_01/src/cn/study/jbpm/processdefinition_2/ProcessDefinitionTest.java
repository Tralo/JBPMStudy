package cn.study.jbpm.processdefinition_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

/**
 * 编写流程定义测试用例
 */
public class ProcessDefinitionTest {

	@Test
	// 将设计业务流程定义，发布到 JBPM 系统中
	public void demo1() {
		// 步骤一: 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应的 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();

		// 步骤三: 进行业务操作
		NewDeployment deployment = repositoryService.createDeployment();// 获得发布对象
		deployment.addResourceFromClasspath("holiday.jpdl.xml");
		deployment.addResourceFromClasspath("holiday.png");
		deployment.deploy(); // 执行发布的动作

	}

	@Test
	// 将设计业务流程定义，发布到 JBPM 系统中
	public void demo2() throws FileNotFoundException {
		// 步骤一: 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应的 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();

		// 步骤三: 进行业务操作
		NewDeployment deployment = repositoryService.createDeployment();// 获得发布对象
		deployment.addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream("holiday.zip")));
		deployment.deploy(); // 执行发布的动作
	}

	@Test
	// 流程定义查看
	public void demo3() {
		// 步骤一: 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应的 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 步骤三: 业务操作
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> list = processDefinitionQuery.list();
		System.out.println(list.size());
		for (ProcessDefinition pd : list) {
			System.out.println("ID:  " + pd.getDeploymentId());
			System.out.println("名称:  " + pd.getName());
			System.out.println("Key:   " + pd.getKey());
			System.out.println("Version:   " + pd.getVersion());
		}

	}

	@Test
	// 查询 holiday-2流程定义对应流程图
	public void demo4() throws IOException {
		// 步骤一: 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应的 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 步骤三: 相关操作
		// 先根据流程定义编号 pdId, 查询流程发布编号 deploymentId
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionId("holiday-2");
		ProcessDefinition processDefinition = processDefinitionQuery.uniqueResult();
		String deploymentId = processDefinition.getDeploymentId();

		// 获取资源名称
		String resourceName = processDefinition.getImageResourceName();
		System.out.println("资源名称：  " + resourceName);

		// 获取图片资源流
		InputStream is = repositoryService.getResourceAsStream(deploymentId, resourceName);
		OutputStream os = new FileOutputStream("/Users/poirot/Desktop/holiday.png");
		int b = -1;
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		os.close();
	}

	@Test
	// 删除流程定义 (根据 id --- deployment表 主键dbid)
	public void demo5() {
		// 步骤一: 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二: 获得对应的 Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 步骤三: 删除流程定义
		repositoryService.deleteDeploymentCascade("20001");
		
		
	}

}
