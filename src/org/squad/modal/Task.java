package org.squad.modal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Task {

	private int taskId;
	private String userid;
	private String opponent;

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	private int status;
	private Timestamp pairingTime;
	private boolean creator;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getPairingTime() {
		return pairingTime;
	}

	public void setPairingTime(Timestamp pairingTime) {
		this.pairingTime = pairingTime;
	}

	public boolean isCreator() {
		return creator;
	}

	public void setCreator(boolean creator) {
		this.creator = creator;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	private List<String> list = new ArrayList<>();

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}
