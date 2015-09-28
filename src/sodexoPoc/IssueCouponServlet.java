package sodexoPoc;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Servlet implementation class IssueCouponServlet
 */
@WebServlet("/IssueCouponServlet")
public class IssueCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueCouponServlet() {
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
		HttpSession session = request.getSession(true);
		UserSodexoDetails userDeails=(UserSodexoDetails)session.getAttribute("userDetailsBean");
		
		Date date = new Date();
		DateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=df.format(date);
        SodexoUserDao user=new SodexoUserDao();
        int pending=userDeails.getPendingAmount();
        boolean resetPin=false;
		for(SodexoDetails sd:userDeails.getSodexoList())
		{
			System.out.println("Issue status for date " + sd.getDate()+ " is "+ request.getParameter(sd.getDate()));
			if(request.getParameter(sd.getDate())!=null)
			{
				sd.setStatus(true);
				pending -=sd.getAmount();
				sd.setIssueDate(dateStr);
				user.updateIsIssused(userDeails.getEmpId(),sd);
				resetPin=true;
			}
			
		}
		if(resetPin)
			user.updateUserPIN(userDeails.getEmpId(),0);
		userDeails.setPendingAmount(pending);
		response.sendRedirect("DistributeSodexo.jsp");
	}

}
