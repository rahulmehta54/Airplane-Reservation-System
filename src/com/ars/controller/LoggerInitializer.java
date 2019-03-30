package com.ars.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class LoggerInitializer
 */
public class LoggerInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger LOG=Logger.getLogger(LoggerInitializer.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoggerInitializer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    

	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();
		String path=context.getRealPath("/");
		String logPropFile=path+"WEB-INF\\log4j.properties";
		PropertyConfigurator.configure(logPropFile);
		LOG.info("Application Initialized");
		}
				
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
