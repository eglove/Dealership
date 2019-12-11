package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Vehicle;

/**
 * Servlet implementation class AddCarServlet
 */
@WebServlet("/AddCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCar() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.sendRedirect("admin.jsp");
		} catch (IOException e) {
			util.Files.LOGGER.severe("Could not redirect to admin.jsp" + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * Generate 10-digit number for VIN, check if it already exists in inventory,
		 * generate new until a unique is found.
		 */
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		ArrayList<Vehicle> temp = (ArrayList<Vehicle>) util.Files.searchFileForVehicle(util.Files.INVENTORYFILE,
				Long.toString(number));
		while (!temp.isEmpty()) {
			number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			temp = (ArrayList<Vehicle>) util.Files.searchFileForVehicle(util.Files.INVENTORYFILE,
					Long.toString(number));
		}

		String vin = Long.toString(number);

		// Parse price to double to calculate discounted price 10% off for bidding use
		double d = 0;
		try {
			d = Double.parseDouble("price");
		} catch (NumberFormatException e) {
			util.Files.LOGGER.severe("Could not parse price as double." + e);
		}

		// Date, price, make, model, carDescription, mainImage, year, daysInInventory,
		// discount, used, kilosRan, bid, vin
		util.Files.appendToFile(util.Files.INVENTORYFILE, request.getParameter("date"), request.getParameter("price"),
				request.getParameter("make"), request.getParameter("model"), request.getParameter("carDescription"),
				request.getParameter("mainImage"), request.getParameter("year"), "0", Double.toString(d - (d * 0.1)),
				request.getParameter("used"), request.getParameter("kilosRan"), "0", vin);

		try {
			response.sendRedirect("admin.jsp");
		} catch (IOException e) {
			util.Files.LOGGER.severe("Could not redirect to admin.jsp" + e);
		}
	}

}
