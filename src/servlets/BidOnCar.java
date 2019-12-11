package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BidOnCar
 */
@WebServlet("/BidOnCar")
public class BidOnCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BidOnCar() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		RequestDispatcher back = request.getRequestDispatcher("details.jsp?vin=" + request.getParameter("vin"));

		Double userBidD = 0.0;
		Double priceD = 0.0;
		Double discountD = 0.0;

		/*
		 * If user entries are able to parse as Double, a check is made to see if they
		 * are within the right range. If yes, they are forwarded to Home.
		 * 
		 * If entry parses but is not within range, redirects back to details.
		 * 
		 * If entry does not parse, redirects back to details.
		 * 
		 * If the user bid is equal to the original price of the car, user buys the car.
		 */
		try {
			userBidD = Double.parseDouble(request.getParameter("userBid"));
			priceD = Double.parseDouble(request.getParameter("price"));
			discountD = Double.parseDouble(request.getParameter("discount"));

			if (userBidD < priceD && userBidD >= discountD) {
				util.Files.updateValue(util.Files.INVENTORYFILE, request.getParameter("vin"), 8,
						Double.toString(userBidD + 1));

				// Add to bidLedger
				util.Files.appendToFile(util.Files.BIDLEDGERFILE, new Date().toString(), request.getParameter("vin"),
						Double.toString(userBidD), request.getParameter("firstName"), request.getParameter("lastName"),
						request.getParameter("phoneNumber"), request.getParameter("email"),
						request.getParameter("streetAddress"), request.getParameter("city"),
						request.getParameter("state"), request.getParameter("zipCode"));
				response.sendRedirect("/Dealership/Home");
			} else if (userBidD.equals(priceD)) {

				util.Files.removeFromFile(util.Files.INVENTORYFILE, request.getParameter("vin"));
				util.Files.appendToFile(util.Files.LEDGERFILE, new Date().toString(), request.getParameter("vin"),
						request.getParameter("firstName"), request.getParameter("lastName"),
						request.getParameter("phoneNumber"), request.getParameter("email"),
						request.getParameter("streetAddress"), request.getParameter("city"),
						request.getParameter("state"), request.getParameter("zipCode"));
				response.sendRedirect("/Dealership/Home");
			} else {
				String msg = "Invalid bid.";
				session.setAttribute("msg", msg);
				back.forward(request, response);
			}
		} catch (NumberFormatException | ServletException | IOException e) {
			try {
				back.forward(request, response);
			} catch (ServletException | IOException f) {
				util.Files.LOGGER.severe("Could not forward to details.jsp." + e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
