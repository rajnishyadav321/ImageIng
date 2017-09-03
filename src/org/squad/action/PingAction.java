package org.squad.action;

import com.opensymphony.xwork2.ActionSupport;

public class PingAction extends ActionSupport {

	private String result = "pinging";

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String execute() {

		return SUCCESS;
	}
}
