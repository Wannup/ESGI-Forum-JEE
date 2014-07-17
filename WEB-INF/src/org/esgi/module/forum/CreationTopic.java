package org.esgi.module.forum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.Message;
import org.esgi.orm.my.model.Sujet;
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
			
			ORM_SEARCH searchSubject = new ORM_SEARCH();
			searchSubject.addConstrainte("titre", sujet);
			ArrayList<Sujet> resultSujet = (ArrayList<Sujet>) ORM.select(Sujet.class, searchSubject);
			
			if(resultSujet.size()>0){
				System.out.println("sujet déjà existant.");
			} else {
				//recup id auteur
				String login = (String) context.getRequest().getSession().getAttribute("username");
				ORM_SEARCH search = new ORM_SEARCH();
				search.addConstrainte("login", login);
				ArrayList<User> results = (ArrayList<User>) ORM.select(User.class, search);
				User u = results.get(0);
				
				Date date = new Date();
				SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
				String date_to_string = dateformatJava.format(date);

				Sujet s = new Sujet(sujet, u.getId(), date_to_string);			
				ORM.save(s);		
	
				resultSujet = (ArrayList<Sujet>) ORM.select(Sujet.class, searchSubject);
				s = resultSujet.get(0);
				Message m = new Message(message, date.toString(), u.getId(), s.getId());
				ORM.save(m);
				
			} 
					
		}
	}
}