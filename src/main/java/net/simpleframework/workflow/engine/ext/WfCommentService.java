package net.simpleframework.workflow.engine.ext;

import net.simpleframework.ado.IParamsValue;
import net.simpleframework.ado.db.IDbEntityManager;
import net.simpleframework.common.coll.ArrayUtils;
import net.simpleframework.module.common.content.impl.AbstractCommentService;
import net.simpleframework.workflow.engine.IWorkflowServiceAware;
import net.simpleframework.workflow.engine.ProcessBean;
import net.simpleframework.workflow.engine.WorkitemBean;
import net.simpleframework.workflow.engine.ext.WfCommentLog.ELogType;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class WfCommentService extends AbstractCommentService<WfComment> implements
		IWfCommentService, IWorkflowServiceAware {

	@Override
	public WfComment getCurComment(final WorkitemBean workitem) {
		return workitem == null ? null : getBean("workitemId=?", workitem.getId());
	}

	@Override
	public void onInit() throws Exception {
		super.onInit();

		final IWfCommentLogService lService = workflowContext.getCommentLogService();

		addListener(new DbEntityAdapterEx() {
			@Override
			public void onAfterInsert(final IDbEntityManager<?> manager, final Object[] beans) {
				super.onAfterInsert(manager, beans);
				for (final Object o : beans) {
					final WfComment c = (WfComment) o;
					lService.insertLog(c, ELogType.history);

					// 更新process
					updateCommentCount(c);
				}
			}

			@Override
			public void onAfterDelete(IDbEntityManager<?> manager, IParamsValue paramsValue) {
				super.onAfterDelete(manager, paramsValue);
				for (final WfComment c : coll(paramsValue)) {
					updateCommentCount(c);
				}
			}

			private void updateCommentCount(WfComment c) {
				final ProcessBean process = pService.getBean(c.getContentId());
				process.setComments(count("contentId=?", process.getId()));
				pService.update(new String[] { "comments" }, process);
			}

			@Override
			public void onAfterUpdate(final IDbEntityManager<?> manager, final String[] columns,
					final Object[] beans) {
				super.onAfterUpdate(manager, columns, beans);
				if (ArrayUtils.isEmpty(columns) || ArrayUtils.contains(columns, "ccomment", true)) {
					for (final Object o : beans) {
						final WfComment comment = (WfComment) o;
						if (lService.getLog(comment.getUserId(), comment.getCcomment(), ELogType.history) == null) {
							lService.insertLog(comment, ELogType.history);
						}
					}
				}
			}
		});
	}
}