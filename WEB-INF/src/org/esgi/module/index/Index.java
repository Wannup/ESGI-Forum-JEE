package org.esgi.module.index;

import org.esgi.web.action.AbstractAction;

public class Index extends AbstractAction{
	
	@Override
	public String getRoute() {
		return "/";
	}
	
	@Override
	public String getLayout() {
		return "default";
	}

}
