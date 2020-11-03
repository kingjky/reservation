package kr.or.connect.todo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection implements AutoCloseable {
	private static final String sqlDriver = "com.mysql.jdbc.Driver";

	private Connection connection;
	private PreparedStatement preparedStatement;
	
	private static final Logger logger = Logger.getRootLogger();
	
	static {
		try {
			Class.forName(sqlDriver);
		} catch (ClassNotFoundException classNotFoundException) {
			logger.error(classNotFoundException.getMessage());
		}
	}

	public DBConnection(String dbUrl, String dbUser, String dbPasswd)
		throws SQLException {
		super();
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
