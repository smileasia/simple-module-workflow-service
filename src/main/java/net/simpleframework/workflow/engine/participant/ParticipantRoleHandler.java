package net.simpleframework.workflow.engine.participant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import net.simpleframework.common.ID;
import net.simpleframework.ctx.script.IScriptEval;
import net.simpleframework.ctx.script.ScriptEvalUtils;
import net.simpleframework.workflow.engine.participant.IParticipantHandler.AbstractParticipantHandler;

/**
 * Licensed under the Apache License, Version 2.0
 * 
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
public class ParticipantRoleHandler extends AbstractParticipantHandler {

	@Override
	public Collection<Participant> getParticipants(final IScriptEval script,
			final Map<String, Object> variables) {
		final ArrayList<Participant> participants = new ArrayList<Participant>();
		final String participant = ScriptEvalUtils.replaceExpr(script, getParticipantType(variables)
				.getParticipant());
		final ID roleId = permission.getRole(participant).getId();
		final Enumeration<ID> users = permission.users(roleId, variables);
		while (users.hasMoreElements()) {
			participants.add(new Participant(users.nextElement(), roleId));
		}
		return participants;
	}
}