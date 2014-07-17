package org.esgi.module.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class UserPage extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/userpage";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Mon espace");
		String login = (String) context.getRequest().getSession().getAttribute("username");
		ORM_SEARCH search = new ORM_SEARCH();
		search.addConstrainte("login", login);
		ArrayList<User> results = (ArrayList<User>) ORM.select(User.class, search);

		User u = results.get(0);
		String nom = u.getLastname();
		String prenom = u.getFirstname();
		String email = u.getEmail();
		String rdate = u.getRegisterDate();
		
		context.getVelocityContext().put("nom", nom);	
		context.getVelocityContext().put("prenom", prenom);
		context.getVelocityContext().put("email", email);
		context.getVelocityContext().put("registerdate", rdate);
		
	}
}