package kr.or.connect.todo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

@WebServlet("/todoType")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoTypeServlet() {
		super();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		
		TodoDao todoDao = new TodoDao();
		TodoDto todoDto = new TodoDto(id, type);
		todoDao.updateTodo(todoDto);
		
		response.getWriter().write("success");
		
	}
}
