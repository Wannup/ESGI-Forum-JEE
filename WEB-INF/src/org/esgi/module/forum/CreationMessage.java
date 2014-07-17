package org.esgi.module.forum;

import java.util.ArrayList;
import java.util.Date;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.Message;
import org.esgi.orm.my.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class CreationMessage extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/forum/creationmessage";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}
	
	

		@Override
		public void execute(IContext context) throws Exception {
			context.getRequest().getSession(true);
			context.getVelocityContext().put("title", "CreationMessage");
			String message = context.getRequest().getParameter("message");
		
			
			if(message!=null){		
				String login = (String) context.getRequest().getSession().getAttribute("username");
				ORM_SEARCH search = new ORM_SEARCH();
				search.addConstrainte("login", login);
				ArrayList<User> results = (ArrayList<User>) ORM.select(User.class, search);
				User u = results.get(0);
				
				Date date = new Date();
				
				long sujetId = 0;
				Message m = new Message(message, date.toString(), u.getId(), sujetId);
				Message mTmp = (Message) ORM.save(m);			
						
			}
		}
	}

