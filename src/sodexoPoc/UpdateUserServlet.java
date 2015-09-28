package sodexoPoc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		SodexoUserDao dao = new SodexoUserDao();
		UserSodexoDetails userDeails = dao.getUserDetails("EmpId",
				Integer.parseInt(userId));
		HttpSession session = request.getSession(true);
		String msg = "";
		if (userDeails != null) {
			request.setAttribute("msg", msg);
			session.setAttribute("userDetailsBean", userDeails);
			response.sendRedirect("ModifyUserData.jsp");
		} else {
			msg = "User Not found";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("ModifyUser.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
