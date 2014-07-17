package org.esgi.module.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Register extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/register";
	}
	@Override
	public String getLayout() {
		return "default";
	}
	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Inscription");
		String fname = context.getRequest().getParameter("fname");
		String lname = context.getRequest().getParameter("lname");
		String email = context.getRequest().getParameter("email");
		String login = context.getRequest().getParameter("login");
		String password = context.getRequest().getParameter("password");
		String passwordCheck = context.getRequest().getParameter("passwordVerification");
		
		if(fname!=null && lname!=null && login!=null && password!=null && email!=null && passwordCheck!=null){
			if(password.equals(passwordCheck)){
				ORM_SEARCH critere = new ORM_SEARCH();
				critere.addConstrainte("login", login);
				ArrayList<User> results = (ArrayList<User>) ORM.loadWithOutPrimaryKey(User.class, critere);			
				if(results.size() > 0){
					System.out.println("Login déjà existant.");
				}else{
					Date date = new Date();
					SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
					String date_to_string = dateformatJava.format(date);
					User u = new User(login, password, email, lname, fname, date_to_string);
					ORM.save(u);
					HttpSession session = context.getRequest().getSession(true);
					session.setAttribute("online", "ok");
					session.setAttribute("username", login);
					System.out.println("Utilisateur: " + login + " s'est connecte.");
				}
			} else {
				System.out.println("password don't match.");
			}
		}
	}
}
