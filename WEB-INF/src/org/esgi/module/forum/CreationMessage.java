package org.esgi.module.forum;

import org.esgi.orm.my.ORM;
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
			String commentaire = context.getRequest().getParameter("commentaire");
			String titre = context.getRequest().getParameter("titre");
			String sujet = context.getRequest().getParameter("sujet");
		
			
			if(message!=null && commentaire!=null && titre!=null && sujet!=null){
			
						
						Message u = new Message(message, commentaire, titre, sujet);
						Message unmessage = (Message) ORM.save(u);
						
			}
		}
	}

