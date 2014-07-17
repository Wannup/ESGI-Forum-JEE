package org.esgi.orm.my;

import java.util.ArrayList;

import org.esgi.orm.my.annotations.ORM_SEARCH;

public interface IORM {

	/** Create or update a record in db. */
	public Object _save(Object o);
	
	/** return an instance of clazz */
	public Object _load(Class<?> c, Object id);
	
	/** delete an record from clazz persistence layer */
	public boolean _remove(Class<?> c, Object id);
	
	public  ArrayList<Object> _select(Class clazz, ORM_SEARCH searchColumn);

	ArrayList<Object> _loadAllTable(Class c);
	
}
