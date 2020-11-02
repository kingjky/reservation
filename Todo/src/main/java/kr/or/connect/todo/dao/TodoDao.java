package kr.or.connect.todo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todo.TodoType;
import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.util.DBConnection;

public class TodoDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbPasswd = "connect123!@#";

	public int addTodo(TodoDto todoDto) {
		int addCount = 0;
		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (
			DBConnection dbConn = new DBConnection(dbUrl, dbUser, dbPasswd).setSQL(sql);) {
			dbConn.getPreparedStatement().setString(1, todoDto.getTitle());
			dbConn.getPreparedStatement().setString(2, todoDto.getName());
			dbConn.getPreparedStatement().setInt(3, todoDto.getSequence());
			addCount = dbConn.getPreparedStatement().executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addCount;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<>();
		String sql = "SELECT id, title, name, sequence, type, SUBSTR(regdate, 1, 10) as regdate FROM todo ORDER BY regdate ASC";
		
		try (DBConnection dbConn = new DBConnection(dbUrl, dbUser, dbPasswd).setSQL(sql);
			ResultSet rs = dbConn.getPreparedStatement().executeQuery();) {
			while (rs.next()) {
				long id = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int sequence = rs.getInt(4);
				String type = rs.getString(5);
				String regDate = rs.getString(6);

				TodoDto todoDto = new TodoDto(id, name, regDate, sequence, title, type);
				list.add(todoDto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		String sql = "UPDATE todo SET type = ? WHERE id = ?;";

		try (DBConnection dbcon = new DBConnection(dbUrl, dbUser, dbPasswd).setSQL(sql);) {
			String newType = getNextType(todoDto.getType());
			dbcon.getPreparedStatement().setString(1, newType);
			dbcon.getPreparedStatement().setLong(2, todoDto.getId());

			updateCount = dbcon.getPreparedStatement().executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateCount;
	}

	private String getNextType(String type) {
		TodoType todoType = TodoType.valueOf(type);
		TodoType[] values = TodoType.values();
		int nextOrder = getNextOrder(todoType.ordinal(), values.length);
		TodoType newTodoType = values[nextOrder];
		return newTodoType.name();
	}

	private int getNextOrder(int order, int maxNum) {
		int nextOrder = order + 1;
		if (nextOrder == maxNum)
			nextOrder = 0;
		return nextOrder;
	}
}