package bg.pragmatic.bookstore.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.pragmatic.bookstore.container.Book;
import bg.pragmatic.bookstore.container.User;
import bg.pragmatic.bookstore.db.Hsqldb;

/**
 * Servlet implementation class MainView
 */
//@WebServlet("/MainView")
public class MainView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Hsqldb db = new Hsqldb();
			db.createTables();
//			db.insertBook(new Book("a", "aa", "aaa", 30, true, 44));
//			db.insertBook(new Book("b", "bb", "bbb", 74, false, 14));
//			db.insertBook(new Book("c", "cc", "ccc", 17, true, 89));
//			db.insertBook(new Book("d", "aa", "aaa", 30, true, 44));
//			db.insertBook(new Book("e", "bb", "bbb", 74, false, 14));
//			db.insertBook(new Book("f", "cc", "ccc", 17, true, 89));
			
			List<Book> books = db.getBookList(new Book());
			
//			db.insertUser(new User("admin", "admin", 0));
//			db.insertUser(new User("ha", "ah", 1));
//			db.insertUser(new User("az", "ti", 1));
//			db.insertUser(new User("toj", "tq", 1));
			
			List<User> users = db.getUserList(new User());
			
			request.setAttribute("books", books);
			request.setAttribute("users", users);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 
//      Forward request to the jsp, so that the jsp can display the result
       	request.getRequestDispatcher("./Store.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
