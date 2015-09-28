package sodexoPoc;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class UpdateUserDataServlet
 */
@WebServlet("/UpdateUserDataServlet")
public class UpdateUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserDataServlet() {
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
		System.out.println("Entering ModifyUserdata doPost");
		try {
			HttpSession session = request.getSession(true);
			UserSodexoDetails userDeails = (UserSodexoDetails) session
					.getAttribute("userDetailsBean");
			userDeails.populateDetailsFromReq(request);
			System.out.println("Retunred populateDetailsFromReq "+userDeails.getRole().getValue());
			SodexoUserDao userDao = new SodexoUserDao();
			String msg = "";
			if (userDao.modifyUserDetails(userDeails,request.getAttribute("updatedSodexoDetails").equals("true"))) {
				msg = "Record updated successfully";
				request.setAttribute("msg", msg);
				System.out.println(msg);
				request.getRequestDispatcher("ModifyUserData.jsp").forward(request,response);
				//response.sendRedirect("ModifyUser.jsp");
			} else {
				msg = "updation failed";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("ModifyUserData.jsp").forward(request,response);
				//response.sendRedirect("ModifyUserData.jsp");
			}
			System.out.println(msg);
			
			//pw.println("<font size='6' color=blue>" + msg + "</font>");
		} catch (Exception e) {
			System.out.println("Acception occured " + e);
			//pw.println(e);
		}
	}

}
