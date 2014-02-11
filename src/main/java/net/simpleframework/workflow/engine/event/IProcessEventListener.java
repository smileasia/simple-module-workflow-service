package net.simpleframework.workflow.engine.event;

import net.simpleframework.workflow.engine.EProcessAbortPolicy;
import net.simpleframework.workflow.engine.InitiateItem;
import net.simpleframework.workflow.engine.ProcessBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public interface IProcessEventListener extends IWorkflowEventListener {

	/**
	 * 流程创建时触发
	 * 
	 * @param initiateItem
	 * @param process
	 */
	void onCreated(InitiateItem initiateItem, ProcessBean process);

	/**
	 * 流程被放弃时触发
	 * 
	 * @param process
	 * @param policy
	 */
	void onAbort(ProcessBean process, EProcessAbortPolicy policy);

	void onDelete(ProcessBean process);

	/**
	 * 流程挂起或恢复时触发
	 * 
	 * @param process
	 */
	void onSuspend(ProcessBean process);

	void onResume(ProcessBean process);

	public static abstract class ProcessAdapter implements IProcessEventListener {

		@Override
		public void onCreated(final InitiateItem initiateItem, final ProcessBean process) {
		}

		@Override
		public void onAbort(final ProcessBean process, final EProcessAbortPolicy policy) {
		}

		@Override
		public void onDelete(final ProcessBean process) {
		}

		@Override
		public void onSuspend(final ProcessBean process) {
		}

		@Override
		public void onResume(final ProcessBean process) {
		}
	}
}
