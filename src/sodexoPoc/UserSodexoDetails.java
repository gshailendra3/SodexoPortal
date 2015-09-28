package sodexoPoc;

import java.util.*;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

public class UserSodexoDetails implements Serializable {

	private int empId = 0;
	private String passwd;
	private String name;
	private String email;
	private int pendingAmount = 0;
	private Role role;
	private int pin;
	private ArrayList<SodexoDetails> sodexoList = new ArrayList<SodexoDetails>();

	public void setPin(int p) {
		pin = p;
	}

	public int getPin() {
		return pin;
	}

	public void setEmpId(int id) {
		empId = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPendingAmount(int amt) {
		this.pendingAmount = amt;
	}

	public int getPendingAmount() {
		return pendingAmount;
	}

	public ArrayList<SodexoDetails> getSodexoList() {
		return sodexoList;
	}

	public void setSodexoList(SodexoDetails sd) {
		sodexoList.add(sd);
	}

	public void setRole(int r) {
		role = Role.getRole(r);
	}

	public Role getRole() {
		return role;
	}

	public void populateDetailsFromReq(HttpServletRequest request) throws Exception {
		System.out.println("Entering populateDetailsFromReq");
		try {
			HttpSession session = request.getSession(true);

			this.setEmpId(Integer.parseInt(request.getParameter("empId")));
			if(request.getParameter("passwd") !=null)
				this.setPasswd(request.getParameter("passwd"));
			this.setName(request.getParameter("name"));
			this.setEmail(request.getParameter("email"));
			if(request.getParameter("role") !=null)
			this.setRole(Integer.parseInt(request.getParameter("role")));
            
			String[] newYears = request.getParameterValues("year");
			String[] newMonths = request.getParameterValues("month");
			String[] newAmount = request.getParameterValues("amount");
			request.setAttribute("updatedSodexoDetails","falses");
			if((this.getRole()!=Role.USER) || (newYears==null) || (newMonths==null) || (newAmount==null))
				return;
			
			

			System.out.println("newYears len "+newMonths.length);
			SodexoDetails sd = null;
			String date = null;
			int amount = 0;
			boolean updatedSodexoDetails = false;
			
			if(this.getSodexoList().size() != newMonths.length )	
			{
				this.getSodexoList().clear();
			}
			Iterator<SodexoDetails> itr=this.getSodexoList().iterator();  
			for (int i = 0; i < newMonths.length; i++) {
				sd=null;
				if((itr!=null) && itr.hasNext())
				{
					sd = itr.next();
				}
				else {
					itr=null;
					sd = new SodexoDetails();
					this.setSodexoList(sd);
				}
				date = newYears[i] + "-" + String.format("%02d",Integer.parseInt(newMonths[i])) + "-01";
				if ((sd.getDate() ==null) || !sd.getDate().equals(date))
				{
					System.out.println("Setting date from to " + sd.getDate()
							+ " -->" + date + "-<<");
					sd.setDate(date);
					updatedSodexoDetails = true;
				}
				amount = Integer.parseInt(newAmount[i]);
				if (sd.getAmount() != amount) {
					System.out.println("Setting amount from to "
							+ sd.getAmount() + " -->" + amount);
					sd.setAmount(amount);
					updatedSodexoDetails = true;
				}

			}
		
		request.setAttribute("updatedSodexoDetails",updatedSodexoDetails==true?"true":"falses");
		} catch (Exception e) {
			System.out.println("Acception occured in populateDetailsFromReq" + e);
			throw e;
		}
	}
}
