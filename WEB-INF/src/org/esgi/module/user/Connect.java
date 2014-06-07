package org.esgi.module.user;

import javax.servlet.http.HttpSession;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Connect extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/connect";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Connexion");
		String login = context.getRequest().getParameter("login");
		if(!login.equals(" ")){
			//if(login.equals("test")){
			System.out.println("fg");
				HttpSession session = context.getRequest().getSession(true);
				session.setAttribute("login",login);	
				context.setFragment("login", login);
			//}
		} else {
			
		}
	}
}
