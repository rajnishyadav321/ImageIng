package org.squad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.squad.dao.TaskDao;

public class SubmitTaskAction {

	private int taskId;
	private String opponent;
	private String userId;
	private List<String> que = new ArrayList<>();
	private List<String> ans = new ArrayList<>();
	private String q1;
	private String q2;
	private String q3;
	private String q4;
	private String q5;
	private String a1;
	private String a2;
	private String a3;
	private String a4;
	private String a5;

	public String getQ1() {
		return q1;
	}

	public void setQ1(String q1) {
		this.q1 = q1;
	}

	public String getQ2() {
		return q2;
	}

	public void setQ2(String q2) {
		this.q2 = q2;
	}

	public String getQ3() {
		return q3;
	}

	public void setQ3(String q3) {
		this.q3 = q3;
	}

	public String getQ4() {
		return q4;
	}

	public void setQ4(String q4) {
		this.q4 = q4;
	}

	public String getQ5() {
		return q5;
	}

	public void setQ5(String q5) {
		this.q5 = q5;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public String getA4() {
		return a4;
	}

	public void setA4(String a4) {
		this.a4 = a4;
	}

	public String getA5() {
		return a5;
	}

	public void setA5(String a5) {
		this.a5 = a5;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getQue() {
		return que;
	}

	public void setQue(List<String> que) {
		this.que = que;
	}

	public List<String> getAns() {
		return ans;
	}

	public void setAns(List<String> ans) {
		this.ans = ans;
	}

	TaskDao taskDao = new TaskDao();

	public String execute() {
		System.out.println(taskId);
		System.out.println(opponent);
		que.add(q1);
		que.add(q2);
		que.add(q3);
		que.add(q4);
		que.add(q5);
		ans.add(a1);
		ans.add(a2);
		ans.add(a3);
		ans.add(a4);
		ans.add(a5);
		int status = 0;
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId = (String) session.getAttribute("email");
		if (opponent != null && !opponent.equals("")) {
			System.out.println("Opponet found on submiss");
			status = taskDao.getOpponentStatus(taskId, opponent);
			if (status == 2) {
				int taskId = ThreadLocalRandom.current().nextInt(0, 10000000 + 1);
				taskDao.submitNew(userId, 0, taskId);
				for (int i = 0; i < que.size(); i++) {
					taskDao.addAnswers(taskId, userId, que.get(i), ans.get(i));
				}
			} else {
				System.out.println("Maki8ng points");
				ArrayList<String> opponentAns = null;
				opponentAns = taskDao.getAns(taskId, opponent);
				int points = 0;
				for (int i = 0; i < ans.size(); i++) {
					if (opponentAns.contains(ans.get(i))) {
						points++;
					}
				}
				taskDao.updatePoint(userId, points);
				taskDao.updatePoint(opponent, points);
				taskDao.updateStatus(taskId, opponent);
			}
		} else {
			int taskId = ThreadLocalRandom.current().nextInt(0, 10000000 + 1);
			taskDao.submitNew(userId, status, taskId);
			for (int i = 0; i < que.size(); i++) {
				taskDao.addAnswers(taskId, userId, que.get(i), ans.get(i));
			}
		}
		// System.out.println(q1+" "+q2+" "+a1+" "+a2);
		return "success";
	}

}
