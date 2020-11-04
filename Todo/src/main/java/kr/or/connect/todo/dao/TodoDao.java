package kr.or.connect.todo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.enumeration.TodoType;
import kr.or.connect.todo.util.DBConnection;

public class TodoDao {
	private final static String dbUrl = "jdbc:mysql://10.113.116.52:13306/user11?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
	private final static String dbUser = "user11";
	private final static String dbPasswd = "user11";

	private final static String insertTodoSQL = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
	private final static String selectTodoSql = "SELECT id, title, name, sequence, type, SUBSTR(regdate, 1, 10) as regdate FROM todo ORDER BY regdate ASC";
	private final static String updateTodoSql = "UPDATE todo SET type = ? WHERE id = ?;";

	private final static Logger logger = Logger.getRootLogger();

	public int addTodo(TodoDto todoDto) {
		int addCount = -1;

		try (DBConnection dbConn = new DBConnection(dbUrl, dbUser, dbPasswd).setSQL(insertTodoSQL);) {
			dbConn.getPreparedStatement().setString(1, todoDto.getTitle());
			dbConn.getPreparedStatement().setString(2, todoDto.getName());
			dbConn.getPreparedStatement().setInt(3, todoDto.getSequence());
			addCount = dbConn.getPreparedStatement().executeUpdate();
		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			logger.error(classNotFoundException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return addCount;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<>();

		try (DBConnection dbConn = new DBConnection(dbUrl, dbUser, dbPasswd).setSQL(selectTodoSql);
			ResultSet resultSet = dbConn.getPreparedStatement().executeQuery();) {
			while (resultSet.next()) {
				long id = resultSet.getLong(1);
				String type = resultSet.getString(5);
				String title = resultSet.getString(2);
				String name = resultSet.getString(3);
				int sequence = resultSet.getInt(4);
				String regDate = resultSet.getString(6);

				TodoDto todoDto = new TodoDto.Builder().id(id).type(type).title(title).name(name).sequence(sequence)
					.regDate(regDate).build();
				list.add(todoDto);
			}
		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			logger.error(classNotFoundException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return list;
	}

	public int updateTodo(TodoDto todoDto) {
		int updateCount = -1;

		try (DBConnection dbcon = new DBConnection(dbUrl, dbUser, dbPasswd).setSQL(updateTodoSql);) {
			String type = todoDto.getType();
			String newType = TodoType.valueOf(type).getNextType();
			dbcon.getPreparedStatement().setString(1, newType);
			dbcon.getPreparedStatement().setLong(2, todoDto.getId());

			updateCount = dbcon.getPreparedStatement().executeUpdate();
		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			logger.error(classNotFoundException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return updateCount;
	}
}
