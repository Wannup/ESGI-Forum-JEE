package org.esgi.orm.my.model;

import java.util.Date;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_TABLE;

public class Sujet {



	@ORM_TABLE("forum")
	
		@ORM_PK
		public long auteurId;
		public String date;
		public String titre;
		public long idS;
		
		public Sujet(String sujet, long id_user, String date){
			this.titre = sujet;
			this.auteurId=id_user;
			this.date=date;
		}
		
		public Sujet(){
		}
		
		public long getId(){
			return this.idS;
		}
		
		public long getid_user(){
			return this.auteurId;
		}
		
		public String getsujet(){
			return this.titre;
		}
		
		public String getdate(){
			return date;
		}
		
		@Override
		public String toString() {
			return "Message [id=" + idS + ", id_user=" + auteurId + ", sujet=" + titre + ", date=" + date +"]";
		}
	}