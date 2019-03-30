package com.ars.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;

import com.ars.bo.BookingBO;
import com.ars.dao.BookingDAO;
import com.ars.dao.LoginDao;
import com.ars.dto.Card;
import com.ars.dto.ClassDetail;
import com.ars.dto.Flight;
import com.ars.dto.FlightNameComparator;
import com.ars.dto.FlightPriceComparator;
import com.ars.dto.FlightTimeComparator;
import com.ars.dto.Passenger;
import com.ars.dto.Ticket;

/**
 * Servlet implementation class SearchController
 */
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(BookingController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public static List<Flight> flightlist = new ArrayList<>();
	public static Integer index = 0;
	public static Flight flightConfirm = null;
	public static List<Passenger> flightConfirmList = null;
	public static List<String> seatNumberList = new ArrayList<>();
	String triptype = "";
	String fromLocation = "";
	String toLocation = "";
	String departure_date = "";
	String return_date = "";
	String travellers = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String path = request.getServletPath();

		LOG.info(path);

		if (path != null) {

			String classType = "";

			if (path.equalsIgnoreCase("/BookNow")) {

				if (request.getParameter("index").equalsIgnoreCase("Search")) {

					DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					fromLocation = request.getParameter("from");
					toLocation = request.getParameter("to");
					departure_date = request.getParameter("dept_date");
					return_date = request.getParameter("return_date");
					LocalDate departureDate = LocalDate.parse(departure_date, df);

					travellers = request.getParameter("noOfTravellers");
					classType = request.getParameter("class_type");

					Flight flight = new Flight();
					ClassDetail classDetail = new ClassDetail();
					classDetail.setType(classType);
					flight.setFromLocation(fromLocation);
					flight.setToLocation(toLocation);
					flight.setDeparturedate(departureDate);
					flight.setNoOfTraveller(Integer.parseInt(travellers));
					flight.setClassDetail(classDetail);
					flight.setNoOfTraveller(Integer.parseInt(travellers));
					List<String> errorList = new ArrayList<>();

					BookingBO flightBooking = new BookingBO();
					triptype = request.getParameter("triptype");
					flightlist = flightBooking.validateSearch(flight, errorList);

					request.setAttribute("triptype", triptype);
					request.setAttribute("from", fromLocation);
					request.setAttribute("to", toLocation);
					request.setAttribute("depatureDate", departure_date);
					request.setAttribute("returnDate", return_date);
					request.setAttribute("traveller", travellers);
					request.setAttribute("class", classType);
					if (flightlist.size() != 0 && (flightlist != null)) {

						request.setAttribute("flightlist", flightlist);

						RequestDispatcher rd = request.getRequestDispatcher("SearchResults.jsp");
						rd.forward(request, response);

					} else {

						request.setAttribute("noresult", "No Records Found");
						RequestDispatcher rd = request.getRequestDispatcher("SearchResults.jsp");
						rd.forward(request, response);

					}

				} else {
					index = Integer.parseInt(request.getParameter("index"));
					HttpSession httpSession = request.getSession(false);

					if (httpSession == null) {

						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					} else {

						HttpSession session = request.getSession(false);
						flightConfirm = flightlist.get(index - 1);

						session.setAttribute("flightConfirm", flightConfirm);
						RequestDispatcher rd = request.getRequestDispatcher("bookingConfirmation.jsp");
						rd.forward(request, response);
					}
				}

			} else if (path.equalsIgnoreCase("/Pay")) {
				if (request.getSession(false) == null) {

					response.sendRedirect("index.jsp");
				} else {
					HttpSession session = request.getSession(false);
					List<Passenger> passengerDetailsList = (List<Passenger>) session
							.getAttribute("passengerDetailsList");

					String cardName = request.getParameter("nameOnCard");
					String cardType = request.getParameter("cardType");
					String cardNumber = request.getParameter("cardNumber");
					String cvv = request.getParameter("cvv");
					String expirationMonth = request.getParameter("expirationMonth");
					String expirationYear = request.getParameter("expirationYear");

					Card ticketPayment = new Card();
					ticketPayment.setCardNumber(Long.parseLong(cardNumber));
					ticketPayment.setCardType(cardType);
					ticketPayment.setExpirationMonth(Integer.parseInt(expirationMonth));
					ticketPayment.setExpirationYear(Integer.parseInt(expirationYear));

					ticketPayment.setIdCardNumber(LoginDao.currentUserIDCardNumber);
					BookingBO paymentbo = new BookingBO();
					if (paymentbo.ValidatePaymentDetails(ticketPayment)) {

						BookingBO passengerDetailsBO = new BookingBO();
						List<Passenger> nList = new ArrayList<>();
						List<Passenger> passengerFinalDetailsList = passengerDetailsBO
								.validatePassenger(passengerDetailsList);

						for (int i = 0; i < passengerFinalDetailsList.size(); i++) {
							passengerFinalDetailsList.get(i).getTicket().getFlightdetails().getClassDetail()
									.setSeatNumber(seatNumberList.get(i));

							nList.add(passengerDetailsList.get(i));

						}

						BookingBO b = new BookingBO();
						b.ticketInfobo(passengerFinalDetailsList);

						RequestDispatcher rd = request.getRequestDispatcher("ticketConfirm.jsp");
						rd.forward(request, response);

					} else {
						LOG.info("Payment Validation Fails");
					}

				}
			} else if (path.equalsIgnoreCase("/BookingConfirm")) {

				String name[] = request.getParameterValues("name");
				String age[] = request.getParameterValues("age");
				String gender[] = request.getParameterValues("gender");
				List<Passenger> passengerDetailsList = new ArrayList<>();

				for (int i = 0; i < flightConfirm.getNoOfTraveller(); i++) {
					Passenger passenger = new Passenger();
					passenger.setPassengerName(name[i]);
					passenger.setAge(Integer.parseInt(age[i]));
					passenger.setGender(gender[i]);
					passengerDetailsList.add(passenger);
				}

				HttpSession session = request.getSession(false);
				session.setAttribute("passengerDetailsList", passengerDetailsList);
				RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
				rd.forward(request, response);

			}

			else if (path.equalsIgnoreCase("/ticketHistory")) {
				if (request.getSession(false) == null) {

					response.sendRedirect("index.jsp");
				} else {

					BookingDAO bookingDAO = new BookingDAO();

					List<Passenger> passenegerList = bookingDAO.fetchTicket(LoginController.customer);

					HttpSession session = request.getSession(false);
					session.setAttribute("passnegerHistoryList", passenegerList);
					RequestDispatcher rd = request.getRequestDispatcher("ticketHistory.jsp");
					rd.forward(request, response);

				}
			} else if (path.equalsIgnoreCase("/CancelTicket")) {
				if (request.getSession(false) == null) {

					response.sendRedirect("index.jsp");
				} else {

					HttpSession session = request.getSession(false);
					List<Passenger> passnegerHistoryList = (List<Passenger>) session
							.getAttribute("passnegerHistoryList");
					Integer cancelIndex = Integer.parseInt(request.getParameter("cancelIndex"));
					BookingDAO bookingDAO = new BookingDAO();
					Passenger cancelledTicket = bookingDAO.ticketCancellation(passnegerHistoryList.get(cancelIndex));

					List<Passenger> passenegerList = bookingDAO.fetchTicket(LoginController.customer);
					session.setAttribute("passnegerHistoryList", passenegerList);
					request.setAttribute("cancelMsg",
							"Your ticket  has been cancelled and money will be refunded in your account within 2-3 business days");
					RequestDispatcher rd = request.getRequestDispatcher("ticketHistory.jsp");
					rd.forward(request, response);
				}
			} else if (path.equalsIgnoreCase("/search")) {

				DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				fromLocation = request.getParameter("from");
				toLocation = request.getParameter("to");
				departure_date = request.getParameter("dept_date");
				return_date = request.getParameter("return_date");
				LocalDate departureDate = LocalDate.parse(departure_date, df);
				travellers = request.getParameter("noOfTravellers");
				classType = request.getParameter("class_type");
				Flight flight = new Flight();
				ClassDetail classDetail = new ClassDetail();
				classDetail.setType(classType);
				flight.setFromLocation(fromLocation);
				flight.setToLocation(toLocation);
				flight.setDeparturedate(departureDate);
				flight.setNoOfTraveller(Integer.parseInt(travellers));
				flight.setClassDetail(classDetail);
				flight.setNoOfTraveller(Integer.parseInt(travellers));
				List<String> errorList = new ArrayList<>();
				BookingBO flightBooking = new BookingBO();
				triptype = request.getParameter("triptype");
				flightlist = flightBooking.validateSearch(flight, errorList);
				if (flightlist.size() != 0 && (flightlist != null)) {
					request.setAttribute("triptype", triptype);
					request.setAttribute("flightlist", flightlist);
					request.setAttribute("from", fromLocation);
					request.setAttribute("to", toLocation);
					request.setAttribute("depatureDate", departure_date);
					request.setAttribute("returnDate", return_date);
					request.setAttribute("traveller", travellers);
					request.setAttribute("class", classType);
					RequestDispatcher rd = request.getRequestDispatcher("SearchResults.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("noresult", "No Records Found");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}

			} else if (path.equalsIgnoreCase("/sort")) {
				String value = request.getParameter("sortby");
				request.setAttribute("triptype", triptype);
				request.setAttribute("from", fromLocation);
				request.setAttribute("to", toLocation);
				request.setAttribute("depatureDate", departure_date);
				request.setAttribute("returnDate", return_date);
				request.setAttribute("traveller", travellers);
				request.setAttribute("class", classType);
				if (value.equalsIgnoreCase("Price")) {
					Collections.sort(flightlist, new FlightPriceComparator());

					request.setAttribute("flightlist", flightlist);
					RequestDispatcher rd = request.getRequestDispatcher("SearchResults.jsp");
					rd.forward(request, response);
				} else if (value.equalsIgnoreCase("time")) {
					Collections.sort(flightlist, new FlightTimeComparator());

					request.setAttribute("flightlist", flightlist);
					RequestDispatcher rd = request.getRequestDispatcher("SearchResults.jsp");
					rd.forward(request, response);

				} else if (value.equalsIgnoreCase("Name")) {
					Collections.sort(flightlist, new FlightNameComparator());

					request.setAttribute("flightlist", flightlist);
					RequestDispatcher rd = request.getRequestDispatcher("SearchResults.jsp");
					rd.forward(request, response);

				}
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
