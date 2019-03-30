package com.ars.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ars.bo.BookingBO;
import com.ars.bo.LoginBO;
import com.ars.dao.BookingDAO;
import com.ars.dao.LoginDao;
import com.ars.dto.Customer;
import com.ars.dto.Flight;
import com.ars.dto.Passenger;
import com.ars.dto.Ticket;



/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(LoginController.class);
       static  Customer customer=new Customer();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		  String path=request.getServletPath();
		
		LOG.info(path);
	   
	     int count=0;
		if(path.equalsIgnoreCase("/SignIn"))
		{
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			
				customer.setEmail(email);
				customer.setPassword(password);
				LoginBO loginBO=new LoginBO();
			
				if(loginBO.validateUser(customer)!=null)
				{
					 loginBO.validateUser(customer);
					
					
					HttpSession session=request.getSession(true);
					session.setAttribute("customer", customer);
				     count=Integer.parseInt(request.getParameter("count"));
					
					if(count<=1)
					{
					RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
					}
					else 
					{
						
						if(BookingController.index>0)
						{
						BookingController.flightConfirm=BookingController.flightlist.get(BookingController.index-1);
						
						session.setAttribute("flightConfirm", BookingController.flightConfirm);
						RequestDispatcher rd=request.getRequestDispatcher("bookingConfirmation.jsp");
						rd.forward(request, response);
						}
						else
						{
							RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
							rd.forward(request, response);
						}
						
						
					}
				}
				else
				{
					LOG.info("Login Invalid Credentials");
					request.setAttribute("invalidcredentials","invalidcredentials");
					RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
		}
		else if(path.equalsIgnoreCase("/register"))
		{
			
			

			
			
			RequestDispatcher rd=null;
				try
				{
				customer.setName(request.getParameter("name"));
				String dateOfBirth=request.getParameter("dob");
				
				DateTimeFormatter dtf=DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate dob=LocalDate.parse(dateOfBirth,dtf);
				
				customer.setDateOfBirth(dob);
				
				customer.setIDCardNumber(request.getParameter("card_number"));
			
				customer.setEmail(request.getParameter("email"));
			
				customer.setPassword(request.getParameter("password"));
			
				customer.setAddress(request.getParameter("address"));
			
				customer.setPhoneNumber(request.getParameter("mobile"));
			
				customer.setGender(request.getParameter("gender"));
			
				customer.setIDCardType(request.getParameter("card_type"));
				
				
				
				}catch (Exception e) {
					if(e instanceof DateTimeParseException)
					{
						request.setAttribute("errMsg","Please Enter DOB in MM/dd/yyyy Format");
						LOG.error("Invalid Date Format Register");
						rd=request.getRequestDispatcher("signup.jsp");
						rd.forward(request,response);
					}
					else if(e instanceof NullPointerException)
					{
						request.setAttribute("errMsg","Please Fill the fields");
						LOG.error("Registering Null Field");
						rd=request.getRequestDispatcher("signup.jsp");
						rd.forward(request,response);
					}
					else if(e instanceof NumberFormatException)
					{
						request.setAttribute("errMsg","Please Fill the fields in proper Format");
						LOG.error(e.getMessage());
						rd=request.getRequestDispatcher("signup.jsp");
						rd.forward(request,response);
					}
				}
			
				
				LoginBO  loginBO= new LoginBO();
				if(loginBO.registerValidate(customer))
				{
					
					request.setAttribute("errMsg","You are Successfully Registered");
					rd=request.getRequestDispatcher("signup.jsp");
					rd.include(request,response);
				}
				else
				{
					
					if(loginBO.errorList.isEmpty())
					{
					request.setAttribute("errMsg","Invalid Registration");
					rd=request.getRequestDispatcher("signup.jsp");
					rd.include(request,response);
					}
					else
					{
						
						rd=request.getRequestDispatcher("signup.jsp");
						rd.include(request,response);
					}
				}
		}
		
		else if(path.equalsIgnoreCase("/changePassword"))
		{
			if(request.getSession(false)==null){ 
				
				response.sendRedirect("index.jsp");}
				else
				{
			RequestDispatcher rd=null;
		rd=request.getRequestDispatcher("changePassword.jsp");
				
		rd.forward(request,response);
		}
		}
		

		else if(path.equalsIgnoreCase("/confirmChangePassword"))
		{
			
if(request.getSession(false)==null){ 
				
				response.sendRedirect("index.jsp");}
				else
				{
					RequestDispatcher rd=null;
					
			  String oldPassword=request.getParameter("oldpassword");
			  String newPassword=request.getParameter("newpassword");
			
			  if(oldPassword.equals(customer.getPassword()))
			  {
				 
				  customer.setPassword(newPassword);
				  LoginDao loginDAO=new LoginDao();
				  if(loginDAO.updatePassword(customer))
				  {
					  request.setAttribute("errMsg", "Password Update Successfully");
						rd=request.getRequestDispatcher("changePassword.jsp");
						rd.forward(request,response);
				  }
			  }
			  else
			  {
				  request.setAttribute("errMsg", "Wrong old Password");
					rd=request.getRequestDispatcher("changePassword.jsp");
					rd.forward(request,response);
			  }
			  
				}
			/*
			RequestDispatcher rd=null;
		rd=request.getRequestDispatcher("changePassword.jsp");
		rd.forward(request,response);*/
		}
		
		else if(path.equalsIgnoreCase("/profileUpdate"))
		{
if(request.getSession(false)==null){ 
				
				response.sendRedirect("index.jsp");}
				else
				{
			RequestDispatcher rd=null;
		rd=request.getRequestDispatcher("profileUpdate.jsp");
		rd.forward(request,response);
		}
		}
		
		else if(path.equalsIgnoreCase("/updateProfile"))
		{
if(request.getSession(false)==null){ 
				
				response.sendRedirect("index.jsp");}
				else
				{
			RequestDispatcher rd=null;
			try
			{
			customer.setName(request.getParameter("name"));
			customer.setEmail(request.getParameter("email"));
			customer.setPhoneNumber(request.getParameter("phoneNumber"));
			customer.setGender(request.getParameter("gender"));
			customer.setAddress(request.getParameter("address"));
			String dateOfBirth=request.getParameter("dob");
			
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate dob=LocalDate.parse(dateOfBirth,dtf);
			
			customer.setDateOfBirth(dob);
			
			}catch (Exception e) {
				if(e instanceof DateTimeParseException)
				{
					LOG.error(e.getMessage());
					request.setAttribute("errMsg","Please Enter DOB in MM/dd/yyyy Format");
					rd=request.getRequestDispatcher("profileUpdate.jsp");
					rd.forward(request,response);
				}
				else if(e instanceof NullPointerException)
				{
					LOG.error(e.getMessage());
					request.setAttribute("errMsg","Please Fill the fields");
					rd=request.getRequestDispatcher("profileUpdate.jsp");
					rd.forward(request,response);
				}
				else if(e instanceof NumberFormatException)
				{
					LOG.error(e.getMessage());
					request.setAttribute("errMsg","Please Fill the fields in proper Format");
					rd=request.getRequestDispatcher("profileUpdate.jsp");
					rd.forward(request,response);
				}
			}
			LoginBO loginBO=new LoginBO();
		
			if(loginBO.validateCustomerProfile(customer))
			{
				request.setAttribute("updateMsg","Profile Updated Successfully");
				 rd=request.getRequestDispatcher("profileUpdate.jsp");
				rd.forward(request, response);
			}
			else
			{
				
				request.setAttribute("updateMsg","Profile Updated Successfully");
				 rd=request.getRequestDispatcher("profileUpdate.jsp");
				rd.forward(request, response);
			}
			
		}
		}
		
		else if(path.equalsIgnoreCase("/ValidateUser"))
		{
			
			String email = request.getParameter("email");
			String cardnumber = request.getParameter("card_number");
			LoginDao ld=new LoginDao();
			if(ld.IsUniqueUser(email,cardnumber)){
				String status="true";
				response.getWriter().write(status);
			}
		}
		
		else if(path.equalsIgnoreCase("/signOut"))
		{
			
			SearchLoginFilter.i=0;
		
	   
		    HttpSession session=request.getSession(false);
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
