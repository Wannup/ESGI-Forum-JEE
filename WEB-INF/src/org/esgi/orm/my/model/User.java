package org.esgi.orm.my.model;

import java.util.Date;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

@ORM_TABLE("users")
public class User {
	@ORM_PK
	public long id;
	public String login;
	public String password;
	public String email;
	public String lastname;
	public String firstname;
	public String registerDate;
	
	public User(String login, String password, String email, String lname, String fname, String registerDate){
		this.login = login;
		this.password = password;
		this.email = email;
		this.lastname = lname;
		this.firstname = fname;
		this.registerDate = registerDate;
	}
	
	public User(){
	}
	
	public long getId(){
		return this.id;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public String getRegisterDate(){
		return this.registerDate;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getLastname(){
		return lastname;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", email=" + email + ", lastname=" + lastname + ", firstname=" + firstname +"]";
	}
}
