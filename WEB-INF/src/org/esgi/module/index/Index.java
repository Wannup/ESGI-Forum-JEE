package org.esgi.module.index;

import java.util.ArrayList;

import org.esgi.orm.my.ORM;
import org.esgi.orm.my.annotations.ORM_SEARCH;
import org.esgi.orm.my.model.Sujet;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Index extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}
	
	@Override
	public void execute(IContext context) throws Exception {		
		context.getRequest().getSession(true);
		
		ORM_SEARCH searchSubject = new ORM_SEARCH();		
		ArrayList<Sujet> subjectList = (ArrayList<Sujet>) ORM.loadAllTable(Sujet.class);
		//System.out.println(subjectList.size());
		context.getVelocityContext().put("items", subjectList);
	}
}
