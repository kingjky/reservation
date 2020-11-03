package kr.or.connect.todo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {
	private Connection connection;
	private PreparedStatement preparedStatement;

	public DBConnection(String dbUrl, String dbUser, String dbPasswd)
		throws SQLException, ClassNotFoundException {
		super();
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	@Override
	public void close() throws Exception {
		preparedStatement.close();
		connection.close();
	}

	public DBConnection setSQL(String sql) throws SQLException {
		preparedStatement = connection.prepareStatement(sql);
		return this;
	}
}
