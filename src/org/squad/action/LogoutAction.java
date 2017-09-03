package org.squad.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	public String execute() {
		HttpSession session = ServletActionContext.getRequest().getSession();

		/*
		 * Here session.invalidate() is not working. Perhaps response get
		 * commited at this line. So, when store interceptor tries to store
		 * action message, it will throw error.
		 */
		session.removeAttribute("email");
		// addActionMessage("You hav logout");
		// System.out.println("You have been logout");
		return SUCCESS;
	}
}
