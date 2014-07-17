package org.esgi.module.forum;

import java.util.ArrayList;
import java.util.Date;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.Message;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class TopicMessageList extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/forum/topicmessagelist";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}
	
	@Override
	public void execute(IContext context) throws Exception {
		context.getRequest().getSession(true);
		context.getVelocityContext().put("title", "TopicMessageList");
		String message = context.getRequest().getParameter("message");
	
		
		if(message!=null){		
			String Message = (String) context.getRequest().getSession().getAttribute("Message");
			ORM_SEARCH search = new ORM_SEARCH();
			search.addConstrainte("Message", Message);
			ArrayList<Message> results = (ArrayList<Message>) ORM.select(Message.class, search);
			Message u = results.get(0);
			
			Date date = new Date();
			
			long sujetId = 3;
			Message m = new Message(message, date.toString(), u.getId(), sujetId);
			System.out.println(m);			
					
		}
}
	}