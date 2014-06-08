package org.esgi.module.user;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.esgi.orm.my.ORM;
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
		ArrayList<Object[]> allUsers = ORM.getAllField(User.class);
		boolean userExist = false;
		int i = 0;
		//User u;
		//u = (User) ORM.load(User.class, 1);
		//System.out.println(u);
		while(userExist || i==allUsers.size()){
			User[] u = (User[])allUsers.get(i);
			System.out.println(u[1].login);
			if(u[i].login.equals(login)){
				userExist=true;
			}
			i++;
		}
		if(userExist){
			HttpSession session = context.getRequest().getSession(true);
			System.out.println(login);

			session.setAttribute("login",login);
			System.out.println(login);
		}
		context.getVelocityContext().put("check", userExist);
			
		
	}
}
