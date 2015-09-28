package sodexoPoc;

import java.sql.*;

//import java.util.ArrayList;
//import java.io.IOException;


public class SodexoUserDao {
	Connection con;
	public static final int pinLen = 999999;

	public SodexoUserDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*
			 * Remote Db connection con = DriverManager.getConnection(
			 * "jdbc:mysql://10.201.100.106:3306/sodexoDb?zeroDateTimeBehavior=convertToNull"
			 * , "sodexoAdmin", "");
			 */
			con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/sodexoDb?zeroDateTimeBehavior=convertToNull",
							"root", "");
		} catch (ClassNotFoundException e) {
			System.out.println("Connection failed " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException occured: smt=" + e.getMessage());
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return (rs);
		} catch (SQLException e) {
			System.out.println("SQLException occured: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// private UserSodexoDetails user=new SodexoUserDao();
	public boolean authenticateUser(String empId, String password) {
		String searchQuery = "select * from sodexoUsers where EmpId='" + empId
				+ "' AND Password='" + password + "'";
		ResultSet rs = null;
		Statement stmt = null;
		System.out.println(searchQuery);
		try {
			stmt = con.createStatement();
			System.out.println(stmt);
			rs = stmt.executeQuery(searchQuery);
			System.out.println(rs);
			boolean isEmpty = rs.next();
			if (isEmpty) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("SQLException occured: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("SQLException occured: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("SQLException occured: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

	public UserSodexoDetails getUserDetails(String key, int value) {
		String searchQuery = "select * from sodexoUsers where " + key + "="
				+ value;
		ResultSet rs = null;
		try {
			System.out.println(searchQuery);
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(searchQuery);
			if (rs.next()) {
				UserSodexoDetails userDetails = new UserSodexoDetails();
				userDetails.setEmpId(rs.getInt("EmpId"));
				userDetails.setName(rs.getString("EmpName"));
				userDetails.setRole(rs.getInt("role"));
				userDetails.setPin(rs.getInt("PIN"));
				userDetails.setEmail(rs.getString("Email"));
				rs.close();
				if (userDetails.getRole() != Role.USER)// Return if role is not user
					return userDetails;

				String sodexoQuery = "select * from sodexoDetails where EmpId="
						+ userDetails.getEmpId();
				rs = stmt.executeQuery(sodexoQuery);
				int pendingAmount = 0;

				while (rs.next()) {
					SodexoDetails sd = new SodexoDetails();
					sd.setAmount(Integer.parseInt(rs.getString("CouponAmount")));
					sd.setDate(rs.getString("CouponMonth"));
					sd.setIssueDate(rs.getString("issueDate"));
					sd.setStatus(rs.getString("isIssued").equals("1") ? true
							: false);
					if (!sd.getStatus()) {
						pendingAmount += sd.getAmount();
					}
					userDetails.setSodexoList(sd);
				}
				userDetails.setPendingAmount(pendingAmount);
				return userDetails;
			}
		} catch (SQLException e) {
			System.out.println("SQLException occured: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("SQLException occured: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean createUser(UserSodexoDetails user) {
		
		String createQuery = "insert into sodexoDb.sodexoUsers (EmpId, Password, EmpName, Email,Role) values (?,?,?,?,?)";
		PreparedStatement pst = null;
		System.out.println("INSIDE CREATE USER  "+user.getEmpId() + " " + user.getName() + " "
				+ user.getEmail());
		try {
			pst = con.prepareStatement(createQuery);
			pst.setInt(1, user.getEmpId());
			pst.setString(2, user.getPasswd());
			pst.setString(3, user.getName());
			pst.setString(4, user.getEmail());
			pst.setInt(5, user.getRole().getValue());
			System.out.println(pst);
			pst.executeUpdate();
			// con.commit();
			pst.close();
			if(user.getRole()==Role.USER)
			{
				createSodexoDetails(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

		return true;
	}

	public boolean modifyUserDetails(UserSodexoDetails userDetails,
			boolean updatedSodexoDetails) {
		String updateQuery = "update sodexoDb.sodexoUsers set Email=?, EmpName=? where EmpId=?";
		PreparedStatement pst = null;
		int empid = userDetails.getEmpId();
		System.out.println("modifyUserDetails");
		try {
			pst = con.prepareStatement(updateQuery);
			pst.setString(1, userDetails.getEmail());
			pst.setString(2, userDetails.getName());
			pst.setInt(3, empid);
			pst.executeUpdate();
			System.out.println(pst);
			// con.commit();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}

		if(updatedSodexoDetails)
		{
			return(modifySodexoDetails(userDetails));
		}

		return true;
	}

	private boolean createSodexoDetails(UserSodexoDetails userDetails) {
		
		String insertQuery = "insert into sodexoDb.sodexoDetails (EmpId, CouponMonth, CouponAmount) values (?,?,?)";

		PreparedStatement pst = null;
		
		try {
			pst = con.prepareStatement(insertQuery);

			for (SodexoDetails sodexo : userDetails.getSodexoList()) {
				pst.setInt(1, userDetails.getEmpId());
				pst.setString(2, sodexo.getDate());
				pst.setInt(3, sodexo.getAmount());
				System.out.println(pst);
				pst.executeUpdate();
				// con.commit();
			}
			// con.commit();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	private boolean modifySodexoDetails(UserSodexoDetails userDetails) {
		String deleteQuery = "delete from sodexoDb.sodexoDetails where EmpId=?";
		String insertQuery = "insert into sodexoDb.sodexoDetails (EmpId, CouponMonth, CouponAmount, isIssued, issueDate) values (?,?,?,?,?)";

		PreparedStatement pst = null;
		
		try {
			pst = con.prepareStatement(deleteQuery);
			pst.setInt(1, userDetails.getEmpId());
			pst.executeUpdate();
			System.out.println(pst);
			// con.commit();
			pst.close();

			pst = con.prepareStatement(insertQuery);

			for (SodexoDetails sodexo : userDetails.getSodexoList()) {
				pst.setInt(1, userDetails.getEmpId());
				pst.setString(2, sodexo.getDate());
				pst.setInt(3, sodexo.getAmount());
				pst.setBoolean(4, sodexo.getStatus());
				pst.setString(5, sodexo.getIssueDate());
				System.out.println(pst);
				pst.executeUpdate();
				// con.commit();
			}
			// con.commit();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean updateIsIssused(int empId, SodexoDetails sd) {
		String updateQuery = "update sodexoDb.sodexodetails set isIssued=true,issueDate=? where EmpId=? and CouponMonth=?";
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(updateQuery);
			pst.setString(1, sd.getIssueDate());
			pst.setInt(2, empId);
			pst.setString(3, sd.getDate());
			pst.executeUpdate();
			System.out.println(pst);
			// con.commit();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean updateUserPIN(int empId, int pin) {
		String createQuery = "update sodexoDb.sodexoUsers set PIN=? where EmpId=?";
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(createQuery);
			pst.setInt(1, pin);
			pst.setInt(2, empId);
			pst.executeUpdate();
			System.out.println(pst);
			// con.commit();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean deleteUserDetails(int empId) {
		String deleteQuery = "delete from sodexoDb.sodexoUsers where EmpId=?";
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(deleteQuery);
			pst.setInt(1, empId);
			pst.executeUpdate();
			System.out.println(pst);
			// con.commit();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

		return true;
	}
}
