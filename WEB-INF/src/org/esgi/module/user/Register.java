package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Register extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/register";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Inscription");
		System.out.println(context.getRequest().getParameter("login"));

	}
}
