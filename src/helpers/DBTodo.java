package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBTodo {
	
	public static ArrayList<Todo> getAllTodos() {
		
		ArrayList<Todo> todos = new ArrayList<>();
		
		Connection con = DBUtil.dbConnect("todo_db", "root", "");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM todos");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				todos.add(new Todo(rs.getInt("id"), rs.getString("name")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
		}
		
		return todos;
		
	}
	
	public static void insert(Todo todo) {
		Connection con = DBUtil.dbConnect("todo_db", "root", "");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO todos(name) values(?)");
			ps.setString(1, todo.getName());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	
	public static void delete(int todo) {
		Connection con = DBUtil.dbConnect("todo_db", "root", "");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM todos WHERE id = ?");
			ps.setInt(1, todo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	
	public static Todo todo(int todoId) {
		
		Todo todo = null;
		
		Connection con = DBUtil.dbConnect("todo_db", "root", "");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM todos WHERE id = ?");
			ps.setInt(1, todoId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				todo = new Todo(rs.getInt("id"), rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
		}
		
		return todo;
		
	}
	
	public static void edit(Todo todo) {
		Connection con = DBUtil.dbConnect("todo_db", "root", "");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE todos SET name = ? WHERE id = ?");
			ps.setString(1, todo.getName());
			ps.setInt(2, todo.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	
}
