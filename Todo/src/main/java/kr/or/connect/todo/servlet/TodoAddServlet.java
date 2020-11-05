package kr.or.connect.todo.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.enumeration.TodoColumn;
import kr.or.connect.todo.util.ValueTypeInspection;

@WebServlet("/todoAdd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String mainUrl = "/Todo/main";
	private static final String formUrl = "/Todo/todoForm";

	private final static Logger logger = Logger.getRootLogger();
	
	public TodoAddServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String title = request.getParameter(TodoColumn.TITLE.getName());
			String name = request.getParameter(TodoColumn.NAME.getName());
			String sequenceStr = request.getParameter(TodoColumn.SEQUENCE.getName());

			if (!ValueTypeInspection.isValidString(title) 
				|| !ValueTypeInspection.isValidString(name)
				|| !ValueTypeInspection.isNumeric(sequenceStr)) {
				throw new Exception("잘못된 입력");
			}

			int sequence = Integer.parseInt(sequenceStr);

			TodoDao todoDao = new TodoDao();
			TodoDto todoDto = new TodoDto.Builder().name(name).sequence(sequence).title(title).build();
			todoDao.addTodo(todoDto);

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.sendRedirect(formUrl);
			return;
		} 
		response.sendRedirect(mainUrl);
	}

}
