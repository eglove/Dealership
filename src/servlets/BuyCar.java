package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuyCar
 */
@WebServlet("/BuyCar")
public class BuyCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyCar() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		util.Files.removeFromFile(util.Files.INVENTORYFILE, request.getParameter("vin"));
		util.Files.appendToFile(util.Files.LEDGERFILE, new Date().toString(), request.getParameter("vin"),
				request.getParameter("firstName"), request.getParameter("lastName"),
				request.getParameter("phoneNumber"), request.getParameter("email"),
				request.getParameter("streetAddress"), request.getParameter("city"), request.getParameter("state"),
				request.getParameter("zipCode"));
		
		RequestDispatcher rs = request.getRequestDispatcher("/Home");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			util.Files.LOGGER.severe("Servlet Exception: Could not forward data to home.jsp." + e);
		} catch (IOException e) {
			util.Files.LOGGER.severe("IO Exception: Could not forward data to home.jsp." + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
