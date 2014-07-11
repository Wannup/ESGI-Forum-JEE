package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Deconnect extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/deconnect";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", " ");
		System.out.println("Deconnexion..");
		context.getRequest().getSession().invalidate();
	}
}