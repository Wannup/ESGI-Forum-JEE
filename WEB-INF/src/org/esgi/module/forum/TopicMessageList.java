package org.esgi.module.forum;

import java.util.ArrayList;
import java.util.Date;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.Message;
import org.esgi.orm.my.model.Sujet;
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
		String idSujet = context.getRequest().getParameter("id");
		
		ORM_SEARCH searchSubject = new ORM_SEARCH();
		searchSubject.addConstrainte("idS", idSujet);
		ArrayList<Sujet> resultSujet = (ArrayList<Sujet>) ORM.select(Sujet.class, searchSubject);
		
		ORM_SEARCH searchMessage = new ORM_SEARCH();
		searchMessage.addConstrainte("sujetId", idSujet);
		ArrayList<Message> messageList = (ArrayList<Message>) ORM.select(Message.class, searchMessage);
		
		context.getVelocityContext().put("title", resultSujet.get(0).getsujet());
		context.getVelocityContext().put("items", messageList);
		
	}
}