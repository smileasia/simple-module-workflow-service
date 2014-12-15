package net.simpleframework.workflow.engine.ext;

import net.simpleframework.common.ID;
import net.simpleframework.module.common.content.AbstractComment;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class WfComment extends AbstractComment {
	/* 工作项id */
	private ID workitemId;
	/* 用户所在部门，取自工作项 */
	private ID deptId;
	/* 任务名称 */
	private String taskname;

	public ID getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(ID workitemId) {
		this.workitemId = workitemId;
	}

	public ID getDeptId() {
		return deptId;
	}

	public void setDeptId(ID deptId) {
		this.deptId = deptId;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	private static final long serialVersionUID = -9144204555895365885L;
}
