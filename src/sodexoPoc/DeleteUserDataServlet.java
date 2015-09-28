package sodexoPoc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUserDataServlet
 */
@WebServlet("/DeleteUserDataServlet")
public class DeleteUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		try {

			  UserSodexoDetails user=new UserSodexoDetails();
			  user.setEmpId(Integer.parseInt(request.getParameter("empId")));

			SodexoUserDao sd = new SodexoUserDao();
			int empId = Integer.parseInt(request.getParameter("empId"));
			String msg = null;
			System.out.println("Delete User Data Servlet");
			if (sd.deleteUserDetails(empId)) {
				msg = "User Deleted Succesfully";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("DeleteUser.jsp").forward(request,response);
			} else {
				msg = "failed to insert the data";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("DeleteUserData.jsp").forward(request,response);
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
