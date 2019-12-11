package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CarDetailServlet
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Details() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String vin = request.getParameter("vin");

		try (BufferedReader reader = new BufferedReader(new FileReader(util.Files.INVENTORYFILE))) {
			String line;
			String[] items = null;
			while ((line = reader.readLine()) != null && line.length() > 0) {
				items = line.split(",");
				if (items[12].contains(vin)) {

					HttpSession session = request.getSession(true);
					long daysInInventory = getDaysInInventory(items);
					// Update inventory (12 = vin for token) at index 7 (daysInInventory), to new
					// daysInInventory
					util.Files.updateValue(util.Files.INVENTORYFILE, items[12], 7, Long.toString(daysInInventory));

					session.setAttribute("date", items[0]);
					session.setAttribute("price", items[1]);
					session.setAttribute("make", items[2]);
					session.setAttribute("model", items[3]);
					session.setAttribute("carDescription", items[4]);
					session.setAttribute("mainImage", items[5]);
					session.setAttribute("year", items[6]);
					session.setAttribute("daysInInventory", items[7]);
					session.setAttribute("discount", items[8]);
					session.setAttribute("used", items[9]);
					session.setAttribute("kilosRan", items[10]);
					session.setAttribute("bid", items[11]);
					session.setAttribute("vin", items[12]);

					// Vehicle will remain on sale if sellToLastBidder returns false (no bids were
					// made)
					if (Double.parseDouble(items[7]) >= 130) {
						boolean sold = util.Files.sellToLastBidder(vin);
						if(sold) {
							redirectHome(request, response);
						}
					}
				}
			}
		} catch (IOException e) {
			util.Files.LOGGER.severe("Could not read from file." + e);
		} catch (NumberFormatException e) {
			util.Files.LOGGER.severe("Could not parse number." + e);
		}

		RequestDispatcher rs = request.getRequestDispatcher("details.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			util.Files.LOGGER.severe("Servlet Exception: Could not forward data to details.jsp." + e);
		} catch (IOException e) {
			util.Files.LOGGER.severe("IO Exception: Could not forward data to details.jsp." + e);
		}
	}

	private void redirectHome(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rs = request.getRequestDispatcher("/Home");
		try {
			rs.forward(request, response);
		} catch (ServletException | IOException e) {
			util.Files.LOGGER.severe("Could not forward to Home." + e);
		}
	}

	private long getDaysInInventory(String[] items) {

		LocalDate today = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate addedToInventory = LocalDate.parse(items[0], formatter);

		return Duration.between(addedToInventory.atStartOfDay(), today.atStartOfDay()).toDays();
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
