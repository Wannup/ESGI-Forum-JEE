package org.esgi.module.forum;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class CreationMessage extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/forum/creationmessage";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}
	
	@Override
	public void execute(IContext context) throws Exception {		
		context.getRequest().getSession(true);
	}
}