package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class UserPage extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/userpage";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Mon espace");
	}
}