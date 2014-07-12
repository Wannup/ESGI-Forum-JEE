package org.esgi.module.infos;


import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Info extends AbstractAction{
	@Override
	public String getRoute() {
		return "/infos/info";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Qui sommes nous");
	}
}