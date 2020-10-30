package kr.or.connect.todo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoAddServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));

		TodoDao todoDao = new TodoDao();
		TodoDto todoDto = new TodoDto(name, sequence, title);
		todoDao.addTodo(todoDto);
		
		response.sendRedirect("/Todo/main");
	}

}
