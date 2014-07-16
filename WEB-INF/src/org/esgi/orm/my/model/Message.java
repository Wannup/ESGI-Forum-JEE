package org.esgi.orm.my.model;

import java.util.Date;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

public class Message {



	@ORM_TABLE("forum")
	
		@ORM_PK
		public long idM;
		public String message;
		public String date;
		public long sujetId;
		public long auteurId;
		
		public Message(String message, String date, long userId, long sujetId){
		
			this.message = message;
			this.date = date;
			this.sujetId = sujetId;
			this.auteurId=userId;
		}
		
		public Message(){
		}
		
		public long getId(){
			return this.idM;
		}
		
		public long getid_user(){
			return this.auteurId;
		}
		
		public long getsujet(){
			return this.sujetId;
		}
		
		public String getmessage(){
			return message;
		}
		
		public String getdate(){
			return date;
		}
		
		@Override
		public String toString() {
			return "Message [id=" + idM + ", id_user=" + auteurId + ", message=" + message
					+ ", date=" + date +"]";
		}
	}
