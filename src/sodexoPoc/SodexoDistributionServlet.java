package sodexoPoc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SodexoDistributionServlet
 */
@WebServlet("/SodexoDistributionServlet")
public class SodexoDistributionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SodexoDistributionServlet() {
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
		 String userId = request.getParameter("userPin");
		 SodexoUserDao user=new SodexoUserDao();
		 UserSodexoDetails userDeails=user.getUserDetails("PIN",Integer.parseInt(userId));
		 
		 HttpSession session = request.getSession(true);
		 if(userDeails!=null)
		 {
			 userDeails.setPin(0);
		 session.setAttribute("userDetailsBean",userDeails);
		 response.sendRedirect("DistributeSodexo.jsp");
		 }else
		 {
			 String errorMsg="User Not found";
			 request.setAttribute("errorMessage",errorMsg);
			 request.getRequestDispatcher("DistributorLoginSuccess.jsp").forward(request,response);
		 }
	}

}
