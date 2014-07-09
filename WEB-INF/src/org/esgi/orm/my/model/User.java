package org.esgi.orm.my.model;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

@ORM_TABLE("users")
public class User {
	@ORM_PK
	public long id;
	public String login;
	public String password;
	public volatile String connectedAt;
	
	public long getId(){
		return this.id;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public String getPassword(){
		return password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", connectedAt=" + connectedAt + "]";
	}
}
