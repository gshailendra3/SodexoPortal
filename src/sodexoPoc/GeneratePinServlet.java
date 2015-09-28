package sodexoPoc;

import java.io.IOException;

import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GeneratePinServlet
 */
@WebServlet("/GeneratePinServlet")
public class GeneratePinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    System.out.println("EmpId passed is" + request.getParameter("EmpId"));
		    int empId=Integer.parseInt(request.getParameter("EmpId"));
			Random generator = new Random(empId+System.currentTimeMillis());
	        int pin=generator.nextInt(SodexoUserDao.pinLen);
	        System.out.println("Generated PIN " + pin);
	        SodexoUserDao sd = new SodexoUserDao();
	        if(sd.updateUserPIN(empId,pin))
	        {
	        response.setContentType("text/plain");
	        response.getWriter().write("Your PIN is "+pin);
	        }else
	        {
	        	response.setContentType("text/plain");
		        response.getWriter().write("Error GEnerating pin ");
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
