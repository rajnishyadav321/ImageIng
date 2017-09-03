package org.squad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.squad.dao.TaskDao;
import org.squad.modal.Question;
import org.squad.modal.Task;

import com.opensymphony.xwork2.ActionSupport;

public class TaskAction extends ActionSupport {

	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	TaskDao taskDao = new TaskDao();

	public String execute() {
		// list.add("rfkjrfj");
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId = (String) session.getAttribute("email");

		task = taskDao.checkActiveTask(userId);
		if (task == null) {
			task = taskDao.createTask();
			task.setCreator(true);
		} else {
			System.out.println("Task found");
			int status = task.getStatus();
			if (status == 1) {
				long diff = System.currentTimeMillis() - task.getPairingTime().getTime();
				// long diffSeconds = diff / 1000;
				long diffMinutes = diff / (60 * 1000);
				if (diffMinutes > 2) {
					System.out.println("time excedded");
					int taskId = task.getTaskId();
					String opponentId = task.getOpponent();
					task = getOpponentTask(taskId, opponentId);

					task.setTaskId(taskId);
					task.setOpponent(opponentId);
				} else {
					task = taskDao.createTask();
					task.setCreator(true);
				}
			} else if (status == 2) {
				task = taskDao.createTask();
				task.setCreator(true);
			} else {
				int taskId = task.getTaskId();
				String opponentId = task.getOpponent();
				task = getOpponentTask(taskId, opponentId);

				task.setTaskId(taskId);
				task.setOpponent(opponentId);
			}
		}

		return SUCCESS;
	}

	public Task getOpponentTask(int taskId, String opponentId) {
		System.out.println("Getting oppponent task");
		return taskDao.getOpponentTask(taskId, opponentId);

		// return SUCCESS;
	}

	/*
	 * public String submit(){
	 * 
	 * }
	 */
}
