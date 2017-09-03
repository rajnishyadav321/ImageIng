package org.squad.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.squad.dao.LoginDao;
import org.squad.modal.User;
import org.squad.service.MD5Service;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {

	User user = new User();

	public String execute() {

		LoginDao loginDao = new LoginDao();

		user.setPassword(MD5Service.md5(user.getPassword()));

		boolean result = loginDao.login(user);

		if (result) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("email", user.getEmail());
			return SUCCESS;
		}
		return ERROR;

	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
