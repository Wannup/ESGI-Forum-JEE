package org.esgi.orm;

import org.esgi.orm.my.model.User;


public class Application {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		
		ORM.mysqlHost 		= "localhost";
		ORM.mysqlDatabase 	= "ormtest";
		ORM.mysqlUser 		= "root";
		ORM.mysqlPassword 	= "p@ssword!";
		
		User u;
		u = (User) ORM.load(User.class, 5); 
		System.out.println(u);
	}
}
