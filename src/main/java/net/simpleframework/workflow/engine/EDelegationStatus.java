package net.simpleframework.workflow.engine;

import static net.simpleframework.common.I18n.$m;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public enum EDelegationStatus {
	/* 开始状态 */
	ready {
		@Override
		public String toString() {
			return $m("EDelegationStatus.ready");
		}
	},
	/* 接收 */
	receiving {
		@Override
		public String toString() {
			return $m("EDelegationStatus.receiving");
		}
	},
	/* 运行 */
	running {
		@Override
		public String toString() {
			return $m("EDelegationStatus.running");
		}
	},
	/* 完成 */
	complete {
		@Override
		public String toString() {
			return $m("EDelegationStatus.complete");
		}
	},
	/* 放弃 */
	abort {
		@Override
		public String toString() {
			return $m("EDelegationStatus.abort");
		}
	},
	/* 拒绝 */
	refuse {
		@Override
		public String toString() {
			return $m("EDelegationStatus.refuse");
		}
	}
}
