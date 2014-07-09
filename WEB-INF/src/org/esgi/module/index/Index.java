package org.esgi.module.index;

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
		
	}
}
