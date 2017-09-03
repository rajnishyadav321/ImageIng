package org.squad.modal;

import java.util.List;

public class Question {

	private String qId;

	public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	private List<Answer> answers;

	public String getQId() {
		return qId;
	}

	public void setQId(String qId) {
		this.qId = qId;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
