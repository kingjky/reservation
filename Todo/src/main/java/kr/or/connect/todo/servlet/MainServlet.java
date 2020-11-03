package kr.or.connect.todo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String mainPage = "/main.jsp";

	public MainServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		TodoDao todoDao = new TodoDao();
		List<TodoDto> todoList = todoDao.getTodos();
		request.setAttribute("todoList", todoList);

		RequestDispatcher requestDispatehcer = request.getRequestDispatcher(mainPage);
		requestDispatehcer.forward(request, response);
	}

}
