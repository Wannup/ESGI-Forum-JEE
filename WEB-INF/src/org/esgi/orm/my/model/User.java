package org.esgi.orm.my.model;

import java.util.Date;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

@ORM_TABLE("users")
public class User {
	@ORM_PK
	public int id;
	public String login;
	public String password;
	public volatile Date connectedAt;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", connectedAt=" + connectedAt + "]";
	}
}
