package kr.or.connect.todo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todo.TodoType;
import kr.or.connect.todo.dto.TodoDto;

public class TodoDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";

	public int addTodo(TodoDto todoDto) {
		int addCount = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
		
		// Try With Resources
		try (
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());

			addCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addCount;
	}
	
	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT id, title, name, sequence, type, SUBSTR(regdate, 1, 10) as regdate FROM todo ORDER BY regdate ASC";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
				while(rs.next()) {
					long id = rs.getLong(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int sequence = rs.getInt(4);
					String type = rs.getString(5);
					String regDate = rs.getString(6);
					
					TodoDto todoDto = new TodoDto(id, name, regDate, sequence, title, type);
					list.add(todoDto); // list에 반복할 때마다 Role인스턴스를 생성하여 list에 추가한다.
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql = "UPDATE todo SET type = ? WHERE id = ?;";
		
		// Try With Resources
		try (
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			String newType = getNextType(todoDto.getType());
			ps.setString(1, newType);
			ps.setLong(2, todoDto.getId());

			updateCount = ps.executeUpdate();
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
		if(nextOrder == maxNum)
			nextOrder = 0;
		return nextOrder;
	}
}
