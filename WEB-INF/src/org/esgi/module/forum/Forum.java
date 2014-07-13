package org.esgi.module.forum;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Forum extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/forum/forum";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}
	
	@Override
	public void execute(IContext context) throws Exception {	
		context.getVelocityContext().put("title", "Forum");
		context.getRequest().getSession(true);
	}
}