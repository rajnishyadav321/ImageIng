package org.squad.action;

import org.squad.dao.UserDao;
import org.squad.modal.User;
import org.squad.service.MD5Service;

import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction implements ModelDriven<User> {

	private User user = new User();

	public String execute() {

		UserDao userDao = new UserDao();
		user.setPassword(MD5Service.md5(user.getPassword()));
		int result = userDao.register(user);
		System.out.println(result);

		return "success";
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
