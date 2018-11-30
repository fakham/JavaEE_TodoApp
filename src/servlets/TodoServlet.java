package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.DBTodo;
import helpers.Todo;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TodoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("submit");
		
		if (op != null && op.equals("Add")) {
			Todo todo = new Todo();
			todo.setName(request.getParameter("name"));
			DBTodo.insert(todo);
		} else if (op != null && op.equals("Delete")) {
			String id = request.getParameter("id");
			DBTodo.delete(Integer.parseInt(id));
		} else if (op != null && op.equals("Edit")) {
			String id = request.getParameter("id");
			Todo todo = DBTodo.todo(Integer.parseInt(id));
			request.setAttribute("todo", todo);
		} else if (op != null && op.equals("Modify")) {
			String id = request.getParameter("id");
			Todo todo = DBTodo.todo(Integer.parseInt(id));
			todo.setName(request.getParameter("name"));
			DBTodo.edit(todo);
			request.setAttribute("todo", null);
		}
		
		ArrayList<Todo> todos = DBTodo.getAllTodos();
		request.setAttribute("todos", todos);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
