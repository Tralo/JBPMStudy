package cn.study.handler_02;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class MyDecisionHandler implements DecisionHandler {

	private static final long serialVersionUID = 1L;

	/**
	 * openException 作用是用来读写流程变量,原理：当运行判断节点，执行handler中decide方法，用decide方法返回值和
	 *  transition 的name 属性比，和哪个一致，就流向哪个 transition
	 */
	@Override
	public String decide(OpenExecution openExecution) {
		return "to 军人半票";
	}

}
