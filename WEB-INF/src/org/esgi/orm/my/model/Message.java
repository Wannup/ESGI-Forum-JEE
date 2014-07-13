package org.esgi.orm.my.model;

import java.util.Date;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

public class Message {



	@ORM_TABLE("forum")
	
		@ORM_PK
		public long id_msg;
		public String message;
		public String commentaire;
		public String date;
		public String titre;
		public String sujet;
		public long id_user;
		
		public Message(String message, String commentaire, String titre, String sujet){
		
			this.message = message;
			this.commentaire = commentaire;
			//this.date = date;
			this.titre = titre;
			this.sujet = sujet;
			//this.id_user=id_user;
		}
		
		public Message(){
		}
		
		public long getId(){
			return this.id_msg;
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
		
		public String getcommentaire(){
			return commentaire;
		}
		
		public String getTitre(){
			return titre;
		}
		
		public String getdate(){
			return date;
		}
		
		@Override
		public String toString() {
			return "Message [id=" + id_msg + ", id_user=" + id_user + ", message=" + message
					+ ", commentaire=" + commentaire + ", titre=" + titre + ", date=" + date +"]";
		}
	}
