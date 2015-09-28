package sodexoPoc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.sql.*;
import javax.servlet.http.HttpSession;
//import java.util.*;
//import javax.naming.*;

//import javax.naming.directory.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("pwd");
		
		    SodexoUserDao user=new SodexoUserDao();
			boolean validUser = user.authenticateUser(userId,password);
			if (validUser) {
				// fetch the session from request, create new session if session
				// is not present in the request
				UserSodexoDetails userDeails=user.getUserDetails("EmpId",Integer.parseInt(userId));
				HttpSession session = request.getSession(true);
				session.setAttribute("Name", userDeails.getName());
				// redirect to success page
				if (userDeails.getRole()==Role.ADMIN){
					request.getRequestDispatcher("AdminLoginSuccess.jsp").forward(request,
							response);
					//response.sendRedirect("AdminLoginSuccess.jsp");
				}else if(userDeails.getRole()==Role.USER){
					
					session.setAttribute("userDetailsBean",userDeails);
					request.getRequestDispatcher("LoginSuccess.jsp").forward(request,
							response);
					//response.sendRedirect("LoginSuccess.jsp");
				}else if(userDeails.getRole()==Role.DISTRIBUTER){
					
					//session.setAttribute("userDetailsBean",userDeails);
					request.getRequestDispatcher("DistributorLoginSuccess.jsp").forward(request,
							response);
					//response.sendRedirect("DistributorLoginSuccess.jsp");
				}
				
			}else {
				// redirect to error page
				request.getRequestDispatcher("LoginFailure.jsp").forward(request,response);
				//response.sendRedirect("LoginFailure.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
