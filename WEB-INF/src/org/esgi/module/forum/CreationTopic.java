package org.esgi.module.forum;

import java.util.ArrayList;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class CreationTopic extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/forum/creationtopic";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}
	
	@Override
	public void execute(IContext context) throws Exception {		
		context.getRequest().getSession(true);
		context.getVelocityContext().put("title", "Nouveau sujet");
		String sujet = context.getRequest().getParameter("sujet");
		String message = context.getRequest().getParameter("message");
	
		
		if(message!=null && sujet!=null){	
			
			//recup id auteur
			String login = (String) context.getRequest().getSession().getAttribute("username");
			ORM_SEARCH search = new ORM_SEARCH();
			search.addConstrainte("login", login);
			ArrayList<User> results = (ArrayList<User>) ORM.loadWithOutPrimaryKey(User.class, search);
			User u = results.get(0);
			
			Sujet s = new Sujet(sujet, message, u.getId());			
			Sujet stmp = (Sujet) ORM.save(s);
					
		}
	}
}