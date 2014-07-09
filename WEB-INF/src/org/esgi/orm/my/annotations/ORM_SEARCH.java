package org.esgi.orm.my.annotations;

import java.util.HashMap;

public class ORM_SEARCH {
	
	
	
	public HashMap<String, String> recherche;

	public ORM_SEARCH() {
		recherche = new HashMap<>();
	}
	
	public ORM_SEARCH(HashMap<String, String> recherche) {
		super();
		this.recherche = recherche;
	}

	public HashMap<String, String> getRecherche() {
		return recherche;
	}

	public void setRecherche(HashMap<String, String> recherche) {
		this.recherche = recherche;
	}
	
	
	/** Ajout une nouvelle contrainte 
	 * 
	 * @param column = nom du champ 
	 * @param value = valeur de crit��re de selection
	 */
	public void addConstrainte(String column, String value){
		this.recherche.put(column, value);
	}

}
