package org.esgi.orm.my;

import org.esgi.orm.my.model.User;


public class Application {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		
		ORM.mysqlHost 		= "localhost";
		ORM.mysqlUser 		= "root";
		ORM.mysqlPassword 	= "";
		
		User u;
		u = (User) ORM.load(User.class, 1); 
		System.out.println(u);
	}
}
