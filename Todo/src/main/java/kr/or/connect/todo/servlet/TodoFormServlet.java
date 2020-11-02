package kr.or.connect.todo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/todoForm")
public class TodoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoFormServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/todoForm.jsp");
		requestDispatcher.forward(request, response);
	}

}
