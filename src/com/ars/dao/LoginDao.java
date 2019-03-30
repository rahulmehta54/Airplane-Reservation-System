package com.ars.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.ars.bo.LoginBO;
import com.ars.constants.LoginQueryConstant;

import com.ars.dto.Customer;
import com.ars.util.DBUtil;


public class LoginDao 
{
	private static final Logger LOG=Logger.getLogger(LoginDao.class);
	public static  String  currentUserIDCardNumber;
	
	/**
	 * Getting Customer details using  emailId or id_card_number and password
	 * @param customer 
	 * @return the available customer  
	 * @exception SQL Exception
	 */

	public Customer authenticCustomer(Customer customer) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn= DBUtil.getConnected();
			if (cn != null) {
				 ps=cn.prepareStatement(LoginQueryConstant.SELECTLOGINQUERY);
				ps.setString(1,customer.getEmail());
				ps.setString(2, currentUserIDCardNumber);
				ps.setString(3, customer.getPassword());
				
				 rs=ps.executeQuery();
				boolean flag=false;
				
				while(rs.next())
				{
					flag=true;
					 
					customer.setCustomerID(rs.getInt(1));
					customer.setIDCardNumber(rs.getString(2));
					
					customer.setName(rs.getString(3));
					customer.setEmail(rs.getString(4));
					customer.setPassword(rs.getString(5));
			         LocalDate ld=LocalDate.parse(rs.getString(6));
					customer.setDateOfBirth(ld);
					customer.setAddress(rs.getString(7));
					customer.setPhoneNumber(rs.getString(8));
					customer.setGender(rs.getString(9));
					customer.setIDCardType(rs.getString(10));
				
				}
			currentUserIDCardNumber=customer.getIDCardNumber();
			
			if(flag)
               return customer;
			else
				return null;
				
			}
			
		} catch (Exception ex) {
			
			LOG.error(ex.getMessage());
			return null;
		}
		finally{
			try {
				cn.close();
			    ps.close();
			    rs.close();
			    
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param customer
	 * @return true if customer is registered successfully
	 */
	public boolean registerCustomerDao(Customer customer) {
	      
				 
			
			Connection cn = null;
			PreparedStatement ps=null;
			
			try {
				cn = DBUtil.getConnected();
				
				if (cn != null) {
					ps = cn.prepareStatement(LoginQueryConstant.RegisterQuerry);
					ps.setString(1, customer.getIDCardNumber());
					ps.setString(2, customer.getName());
					ps.setString(3, customer.getEmail());
					ps.setString(4, customer.getPassword());
					Date d = Date.valueOf(customer.getDateOfBirth());
					
					ps.setDate(5, d);
					ps.setString(6, customer.getAddress());
					ps.setString(7, customer.getPhoneNumber());
					ps.setString(8, customer.getGender());
					ps.setString(9, customer.getIDCardType());
                
					int count = ps.executeUpdate();
	                
					if (count==1)
						return true;

				}
			} catch (Exception ex) {
				LOG.error(ex.getMessage());
				LoginBO.errorList.add("Please Enter unique email/ID Card Number");
				return false;
			}finally{
				try {
					cn.close();
				    ps.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage());
				}
				
			}

			return false;
		}
	
	/**
	 * 
	 * @param customer
	 * @return ture if password is updated successfully
	 */
	public boolean updatePassword(Customer customer)
	  {
		  Connection cn=null;
			PreparedStatement ps=null;
			try {
				cn= DBUtil.getConnected();
				if (cn != null) {
					 ps=cn.prepareStatement(LoginQueryConstant.UPDATEPASSWORDQUERY);
					
					ps.setString(1,customer.getPassword());
					ps.setString(2,LoginDao.currentUserIDCardNumber);
					int count=ps.executeUpdate();
					if(count==1)
					{
						return true;
					}
				}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					return false;
				}
				finally{
					
						try {
							cn.close();
							ps.close();
						} catch (SQLException e) {
							
							LOG.info(e.getMessage());
						}
					}
						
					
		return false;
		  
	  }
	
	/**
	 * 
	 * @param emailUser
	 * @param cardNumber
	 * @return false if customer already exists
	 */
	
	
	
	public boolean IsUniqueUser(String emailUser, String cardNumber) {
		
		
		Connection cn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			cn = DBUtil.getConnected();
			cn.setAutoCommit(false);
			if(cn!=null)
			{
				 ps=cn.prepareStatement(LoginQueryConstant.SearchQuerry);
				ps.setString(1,emailUser);
				ps.setString(2, cardNumber);
			 rs=ps.executeQuery();
				if(rs.next())
				{
					
					
					return true;
				}
				else
				{
					
					return false;
				}
			
			}
			
		}catch (Exception e) {
			try {
				ps.close();
				rs.close();
				cn.close();
			} catch (SQLException e1) {
				LOG.info(e1.getMessage());
			}
			
			LOG.error(e.getMessage());
		}

		return false;
	}
	
	/**
	 * 
	 * @param customer
	 * @return true if profile edit is successfull
	 */
	  public boolean updateCustomerProfile(Customer customer)
	  {
		  
		  
			Connection cn=null;
			PreparedStatement ps=null;
			try {
				cn= DBUtil.getConnected();
				if (cn != null) {
					ps=cn.prepareStatement(LoginQueryConstant.UPDATECUSTOMERPROFILEQUERY);
					
					ps.setString(1, customer.getName());
					ps.setString(2, customer.getEmail());
					ps.setString(3,customer.getPhoneNumber());
					ps.setString(4,customer.getGender());
					ps.setString(5,customer.getAddress());
					
					Date dateOfBirth=Date.valueOf(customer.getDateOfBirth());
					
					ps.setDate(6, dateOfBirth);
					ps.setString(7,LoginDao.currentUserIDCardNumber);
					
					ps.executeUpdate();
				}
				} catch (Exception ex) {
					LOG.error(ex.getMessage());
					return false;
				}
				finally{
					
						try {
							cn.close();
						} catch (SQLException e) {
							LOG.error(e.getMessage());
							e.printStackTrace();
						}
						finally{
							try {
								cn.close();
							    ps.close();
							} catch (SQLException e) {
								LOG.error(e.getMessage());
							}
						}
					}
						
					
		return true;
		
	  }
	  








}
