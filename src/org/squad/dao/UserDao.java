package org.squad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.squad.modal.User;

public class UserDao {

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

	public int register(User user) {

		int result = 0;
		PreparedStatement stmt = null;
		String query = "insert into user(email,username,password,points) values(?,?,?,?)";
		try {
			// Register JDBC Driver
			Class.forName(DRIVER_NAME).newInstance();
			// open a connection
			connection = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
			// connection.setAutoCommit(false);
			// Create Statement
			stmt = connection.prepareStatement(query);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getPoints());
			result = stmt.executeUpdate();// Execute insert statement

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
		return result;

	}

}
