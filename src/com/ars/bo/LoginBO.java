package com.ars.bo;


import java.time.LocalDate;
import java.util.*;

import org.apache.log4j.Logger;

import com.ars.dao.LoginDao;
import com.ars.dto.Customer;

public class LoginBO
{
	
	
	public static List<String> errorList=new ArrayList<>();
	private static final Logger LOG=Logger.getLogger(LoginBO.class);

	/**
	 * 
	 * @param customer - object of dto class
	 * @return - returns Customer if credentials are validated
	 */
	public Customer validateUser(Customer customer) 
	{
	 if(checkCustomer(customer))
	 {
		 LoginDao loginDAO=new LoginDao();
		
			
		if( loginDAO.authenticCustomer(customer)!=null)
		{
			
			return loginDAO.authenticCustomer(customer);
		}
	 }
	    LOG.info("Authentication Fail");
		return null;
	}
	
	/**
	 * 
	 * @param customer -  object of dto class
	 * @return - email and password server side validation.
	 */
	
	public boolean checkCustomer(Customer customer)
	{
		String emailPattern="\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b" ;
		String password= "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}";
		
		
		if(customer.getEmail().matches(emailPattern) && customer.getPassword().matches(password))
		{
			return true;
		}
		
		
		 LOG.info("Entering wrong credentials");
		return false;
	}
	/**
	 * 
	 * Id card server side validation
	 */
	public boolean validateIDCard(Customer customer)
	  {
		String pattern;

		if(customer.getIDCardType().equalsIgnoreCase("Pan Card"))
		{
			
			pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}";
			if(customer.getIDCardNumber().matches(pattern))
			{
				return true;
			}
			else
			{
				errorList.add("Please Enter a Valid Pan Card Number");
				LOG.info("Invalid Pan Card Number");
				return false;
			}
			
				
		}

		else if(customer.getIDCardType().equalsIgnoreCase("Aadhaar Card"))
		{
			
			pattern="^[0-9]{12}$";
			if(customer.getIDCardNumber().trim().matches(pattern))
			{
				return true;
			}
			else
			{
				errorList.add("Please Enter a Valid Aadhar Card Number");
				LOG.info("Invalid Aadhaar Card Number");
				return false;
			}
		}

		LOG.info("Inavalid Aadhaar/Pan Card Number");
		return false;

	  }
	  /**
	   * 
	   *UserName server side validation
	   */
	  public boolean validateName(Customer customer)
	  {
			
		  String pattern="^[a-zA-Z. ]{2,}$";
			if(customer.getName().trim().matches(pattern))
				return true;
			else
			{
				LOG.info("Inavalid Customer Name");
				errorList.add("Please Enter a Valid User Name");
				return false;
			}

		  
	  }
	  /**
	   * Email server side validation
	   */
	  public boolean validateEmail(Customer customer)
	  {
		  String pattern="\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
			if(customer.getEmail().matches(pattern))
				return true;
			else{
				LOG.info("Inavalid Email");
			errorList.add("Please Enter a Valid Aadhar Card Number");
				return false;
			}
		  
	  }
	  /**
	   * Password server side validation
	   */
	  public boolean validatePassword(Customer customer)
	  {
		  String pattern= "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}";
			if(customer.getPassword().matches(pattern))
				return true;
			else
				LOG.info("Inavalid Password");
			errorList.add("Psssword must contain atleast 1 digit,1 lowercase,1 uppercase,1 special character,no whitespace and length is between 8 to 15");
			return false;
		  
	  }
	  /**
	   * Date Of Birth server side validation
	   */
	  public boolean validateDateOfBirth(Customer customer)
	  {
		  LocalDate dt= LocalDate.now();
			if(customer.getDateOfBirth().isBefore(dt))
			{
				return true;
			}
			else
				LOG.info("Inavalid Date Of Birth");
			errorList.add("Please Enter Date Of Birth Before current Date");
				return false;
		  
	  }
	  /**
	   * Address server side validation
	   */
	  public boolean validateAddress(Customer customer)
	  {
		  if(customer.getAddress().trim().length()>0 && customer.getAddress().trim().length()<30)
		  return true;
		  else
			  LOG.info("Inavalid Address");
		  errorList.add("Address must be between 1 to 30 characters");
		  return false;
		  
	  }
	  /**
	   * Phone number server side validation
	   */
	  public boolean validatePhoneNumber(Customer customer)
	  {
		  String pattern="[0-9]{10}";
			if(customer.getPhoneNumber().matches(pattern))
				return true;
			else
				LOG.info("Inavalid Phone Number");
			errorList.add("Please Enter a Valid Phone Number");
				return false;
		  
	  }
	  public boolean validateGender(Customer customer)
	  {
         return true;
			
		  
	  }
	  
	  /**
	   * Email server side validation
	   */
	  public boolean checkEmail(Customer customer)
		{

			String pattern="\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
			if(customer.getEmail().matches(pattern))
				return true;
			else
				LOG.info("Inavalid Email");
			errorList.add("Please Enter a Valid Email");
			return false;

		}

	  /**
	   * validating registration values
	   */
	public boolean registerValidate(Customer customer)
	{
		if(validateName(customer)==true && validateEmail(customer)==true && validatePassword(customer) && validateDateOfBirth(customer)==true && validatePhoneNumber(customer)==true && validateGender(customer)==true && validateIDCard(customer)==true)
		{
			
			 LoginDao loginDAO=new LoginDao();

			if(loginDAO.registerCustomerDao(customer)==true)
			return true;
		}
		else
		{	
			return false;
		}
		return false;
	}
	
	/**
	 * 
	 *returns true if all validations are true
	 */
	
	public boolean validateCustomerProfile(Customer customer)
	{
		if(validateName(customer)==true && validateEmail(customer)==true && validatePassword(customer) && validateDateOfBirth(customer)==true && validatePhoneNumber(customer)==true && validateGender(customer)==true && validateIDCard(customer)==true)
		{
			
			 LoginDao loginDAO=new LoginDao();

			if(loginDAO.updateCustomerProfile(customer)==true)
			return true;
		}
		else
		{	
			return false;
		}
		return false;
	}

}

















