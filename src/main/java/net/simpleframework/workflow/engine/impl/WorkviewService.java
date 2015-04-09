package net.simpleframework.workflow.engine.impl;

import java.util.ArrayList;
import java.util.List;

import net.simpleframework.ado.query.IDataQuery;
import net.simpleframework.common.ID;
import net.simpleframework.ctx.permission.PermissionUser;
import net.simpleframework.ctx.service.ado.db.AbstractDbBeanService;
import net.simpleframework.workflow.engine.IWorkviewService;
import net.simpleframework.workflow.engine.WorkitemBean;
import net.simpleframework.workflow.engine.WorkviewBean;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class WorkviewService extends AbstractDbBeanService<WorkviewBean> implements
		IWorkviewService {

	@Override
	public IDataQuery<WorkviewBean> getWorkviewsList(final ID userId) {
		return query("userId=?", userId);
	}

	@Override
	public List<WorkviewBean> createWorkviews(final WorkitemBean workitem, final ID... userIds) {
		final List<WorkviewBean> list = new ArrayList<WorkviewBean>();
		for (final ID id : userIds) {
			final ID processId = workitem.getProcessId();
			if (getWorkviewBean(processId, id) != null) {
				continue;
			}
			final WorkviewBean workview = createBean();
			workview.setWorkitemId(workitem.getId());
			workview.setProcessId(processId);

			final PermissionUser user = permission.getUser(id);
			workview.setUserId(user.getId());
			workview.setUserText(user.getText());
			workview.setDeptId(user.getDept().getId());
			final ID domainId = user.getDept().getDomainId();
			if (domainId != null) {
				workview.setDomainId(domainId);
			}
			insert(workview);
		}
		return list;
	}

	@Override
	public WorkviewBean getWorkviewBean(final Object processId, final Object userId) {
		return getBean("processId=? and userId=?", getIdParam(processId), getIdParam(userId));
	}
}