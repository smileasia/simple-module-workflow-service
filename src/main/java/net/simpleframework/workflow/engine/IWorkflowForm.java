package net.simpleframework.workflow.engine;

import java.util.Map;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IWorkflowForm {

	String getFormForward();

	/**
	 * 完成当前工作项
	 * 
	 * @param parameters
	 * @param workitemComplete
	 */
	void onComplete(Map<String, String> parameters, WorkitemComplete workitemComplete);

	/**
	 * 给流程变量赋值
	 * 
	 * @param variables
	 */
	void bindVariables(Map<String, Object> variables);
}
