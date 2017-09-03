package org.squad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.squad.modal.Answer;
import org.squad.modal.Question;
import org.squad.modal.Task;

public class TaskDao {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";// JDBC
																		// Driver
																		// name
	private static final String URL = "jdbc:mysql://sql12.freemysqlhosting.net:3306/";// Database
																						// URL
	private static final String DB_NAME = "sql12192741";
	// Database credentials
	private static final String USER_NAME = "sql12192741";
	private static final String PASSWORD = "uBBIDxDBa5";

	Connection connection;

	public Task createTask() {

		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Task task = new Task();
		Map<String, List<Answer>> map = new HashMap<>();
		// String query="";
		String query = "SELECT answers.q_id,answers.ans_id FROM answers "
				+ "INNER JOIN (SELECT * FROM questions ORDER BY RAND() LIMIT 5) AS "
				+ "Result ON Result.`q_id`=answers.`q_id`;";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);

			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				// System.out.println("Found");
				String key = resultSet.getString(1);
				String value = resultSet.getString(2);
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<Answer>());
				}
				Answer answer = new Answer();
				answer.setAnsId(value);
				map.get(key).add(answer);
			}
			List<Question> questions = new ArrayList<>();
			for (Entry<String, List<Answer>> entry : map.entrySet()) {
				System.out.println("Msp " + entry.getKey());
				Question question = new Question();
				question.setqId(entry.getKey());

				question.setAnswers(entry.getValue());
				questions.add(question);
			}
			task.setQuestions(questions);
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return task;

	}

	public Task checkActiveTask(String userId) {

		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Task activeTask = null;
		
		String query = "SELECT * FROM active_tasks WHERE (active_tasks.`status`=0 OR active_tasks.`status`=1) AND user_id!=? ORDER BY RAND() LIMIT 1;";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);
				stmt.setString(1, userId);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				activeTask = new Task();
				activeTask.setTaskId(resultSet.getInt(1));
				activeTask.setOpponent(resultSet.getString(2));
				activeTask.setPairingTime(resultSet.getTimestamp(3));
				activeTask.setStatus(resultSet.getInt(4));
				activeTask.setCreator(resultSet.getBoolean(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return activeTask;
	}

	public Task getOpponentTask(int taskId, String opponentId) {

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet resultSet = null;
		Task task = new Task();
		Map<String, List<Answer>> map = new HashMap<>();
		String query1 = "SELECT `answers`.`q_id`, `answers`.`ans_id` FROM `answers` INNER JOIN task_creator_questions ON `answers`.`q_id`=task_creator_questions.`q_id` WHERE task_creator_questions.`task_id`=? AND task_creator_questions.`user_id`=?;";
		String query2 = "UPDATE `active_tasks` SET `active_tasks`.`status`=1 WHERE `task_id`=? AND user_id=?;";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			connection.setAutoCommit(false);
			// Create Statement
			stmt1 = connection.prepareStatement(query1);
			stmt2 = connection.prepareStatement(query2);
			stmt1.setInt(1, taskId);
			stmt1.setString(2, opponentId);
			stmt2.setInt(1, taskId);
			stmt2.setString(2, opponentId);
			resultSet = stmt1.executeQuery();

			// System.out.println("Found");
			while (resultSet.next()) {
				// System.out.println("Found");
				String key = resultSet.getString(1);
				String value = resultSet.getString(2);
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<Answer>());
				}
				Answer answer = new Answer();
				answer.setAnsId(value);
				map.get(key).add(answer);
			}
			List<Question> questions = new ArrayList<>();
			for (Entry<String, List<Answer>> entry : map.entrySet()) {
				System.out.println("Msp " + entry.getKey());
				Question question = new Question();
				question.setqId(entry.getKey());

				question.setAnswers(entry.getValue());
				questions.add(question);
			}
			task.setQuestions(questions);
			stmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt1 != null) {
					stmt1.close();
				}
				if (stmt2 != null) {
					stmt2.close();
				}
				if (connection != null) {
					connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return task;

		// return null;
	}

	public int submitNew(String userId, int status, int taskId) {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		int result = 0;
		Task task = new Task();
		Map<String, List<Answer>> map = new HashMap<>();
		// String query="";
		String query = "insert into active_tasks(user_id,status,task_id) values(?,?,?)";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);
			stmt.setString(1, userId);
			stmt.setInt(2, status);
			stmt.setInt(3, taskId);
			// resultSet=stmt.upda();
			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return result;
	}

	public int addAnswers(int taskId, String userId, String qId, String ansId) {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		int result = 0;
		Task task = new Task();
		Map<String, List<Answer>> map = new HashMap<>();
		// String query="";
		String query = "insert into task_creator_questions values(?,?,?,?)";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, taskId);
			stmt.setString(2, userId);
			// stmt.setInt(3, taskId);
			stmt.setString(3, qId);
			stmt.setString(4, ansId);
			// resultSet=stmt.upda();
			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return result;
	}

	public void submitAgainst() {

	}

	public int getOpponentStatus(int taskId, String opponent) {

		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		int status = -1;
		Task activeTask = null;
		Map<String, List<Answer>> map = new HashMap<>();
		String query = "select status from active_tasks where task_id=? and user_id=?";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, taskId);
			stmt.setString(2, opponent);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				status = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return status;
	}

	public ArrayList<String> getAns(int taskId, String userId) {

		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		ArrayList<String> ans = new ArrayList<>();
		// Task activeTask=null;
		// Map<String,List<Answer>> map=new HashMap<>();
		String query = "SELECT ans_id FROM task_creator_questions WHERE task_id=? AND `user_id`=?;";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, taskId);
			stmt.setString(2, userId);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				ans.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return ans;
	}

	public int updatePoint(String userId, int point) {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		int result = 0;

		String query = "UPDATE `user` SET points=points+? WHERE email=?;";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, point);
			stmt.setString(2, userId);

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return result;

		// return 0;
	}

	public int updateStatus(int taskId, String opponent) {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		int result = 0;

		String query = "UPDATE active_tasks SET status=2 WHERE task_id=? and user_id=?;";

		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);

			stmt.setInt(1, taskId);
			stmt.setString(2, opponent);

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					// connection.setAutoCommit(true);
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(task.getTaskId());
		return result;
	}
}
