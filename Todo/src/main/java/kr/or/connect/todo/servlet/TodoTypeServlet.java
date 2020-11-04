package kr.or.connect.todo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.enumeration.TodoColumn;
import kr.or.connect.todo.enumeration.TodoType;

@WebServlet("/todoType")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successMessage = "success";

	public TodoTypeServlet() {
		super();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		long id = Integer.parseInt(request.getParameter(TodoColumn.ID.getName()));
		String type = request.getParameter(TodoColumn.TYPE.getName());

		TodoDao todoDao = new TodoDao();
		TodoDto todoDto = new TodoDto.Builder().id(id).type(type).build();
		todoDao.updateTodo(todoDto);

		response.getWriter().write(successMessage);
	}
}
