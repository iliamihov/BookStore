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
import bg.pragmatic.bookstore.logic.Controller;


public class LoginScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginScreen() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			User usr = new User();
			
			usr.setName(request.getParameter("User"));
			usr.setPassword(request.getParameter("Password"));
			
			Controller session = new Controller();
			if(session.isloggedIn(usr)){
				
				request.getSession().setAttribute("isLoggedIn", true);
				request.getRequestDispatcher("./Store").forward(request, response);
			} else {
	 
				request.setAttribute("Message", "Invalid User or Password! Please try again.");
				request.getRequestDispatcher("./Login.jsp").forward(request, response); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
