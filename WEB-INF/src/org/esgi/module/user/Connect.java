package org.esgi.module.user;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.User;
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
		String password = context.getRequest().getParameter("password");
		ORM_SEARCH search = new ORM_SEARCH();
		search.addConstrainte("login", login);
		
		
		ArrayList<User> results = (ArrayList<User>) ORM.loadWithOutPrimaryKey(User.class, search);
		String error = " ";
		if(login != null && password != null){
			boolean connectOk = false;
			HttpSession session = context.getRequest().getSession(true);
			if(results.size() > 0){
				User u = results.get(0);
				connectOk = password.equals(u.getPassword());   	
			}
			
			if(!connectOk){
				error = "Mot de passe incorrect !";	
			} else {
				session.setAttribute("online", "ok");
				session.setAttribute("username", login);
				System.out.println("Utilisateur: " + login + " s'est connecte.");
				
			}
		}
		context.getVelocityContext().put("connectNotOk", error);
		
	}
}
