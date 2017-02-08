package cn.study.jbpm.initdb_1;

import org.jbpm.api.Configuration;
import org.junit.Test;

/**
 * 初始化 JBPM 环境
 */
public class JBPMInitTest {
	
	@Test
	public void demo1(){
		// 1. 定义配置类，用来加载 jbpm 配置文件
		Configuration configuration = new Configuration();
		// 2. 加载配置文件，初始化 jbpm 环境
		configuration.buildProcessEngine();
		
	}
	
	
}
