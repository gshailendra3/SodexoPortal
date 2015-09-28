package sodexoPoc;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.sql.*;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		try {
			  UserSodexoDetails userDetails=new UserSodexoDetails();
			  userDetails.populateDetailsFromReq(request);
			
			SodexoUserDao dao = new SodexoUserDao();

			String msg = null;
			if (dao.createUser(userDetails)) {
				msg = "Record has been inserted";
				System.out.println(msg);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("AdminLoginSuccess.jsp").forward(request,response);
				//response.sendRedirect("AdminLoginSuccess.jsp");
			} else {
				msg = "User ["+userDetails.getEmpId()+"] Already Exist";
				System.out.println(msg);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("CreateUser.jsp").forward(request,response);
				//response.sendRedirect("CreateUser.jsp");
				return;
			}
			System.out.println(msg);
			pw.println("<font size='6' color=blue>" + msg + "</font>");
		} catch (Exception e) {
			System.out.println("Acception occured "+e);
			pw.println(e);
		}

	}

}
