package org.esgi.module.forum;

import java.util.Date;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

public class Sujet {



	@ORM_TABLE("forum")
	
		@ORM_PK
		public long id_user;
		public String message;
		public String date;
		public String sujet;
		public long id_sjt;
		
		public Sujet(String sujet, String message, long id_user){
		
			this.message = message;
			this.sujet = sujet;
			this.id_user=id_user;
		}
		
		public Sujet(){
		}
		
		public long getId(){
			return this.id_sjt;
		}
		
		public long getid_user(){
			return this.id_user;
		}
		
		public String getsujet(){
			return this.sujet;
		}
		
		public String getmessage(){
			return message;
		}
		
		public String getdate(){
			return date;
		}
		
		@Override
		public String toString() {
			return "Message [id=" + id_sjt + ", id_user=" + id_user + ", message=" + message
					+ ", sujet=" + sujet + ", date=" + date +"]";
		}
	}