package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Vehicle;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<Vehicle> inventory = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(util.Files.INVENTORYFILE))) {
			String line;
			String[] items = null;
			while ((line = reader.readLine()) != null) {
				items = line.split(",");
				inventory.add(new Vehicle(items[0], items[1], items[2], items[3], items[4], items[5], items[6],
						items[7], items[8], items[9], items[10], items[11], items[12]));
			}
		} catch (IOException e) {
			util.Files.LOGGER.severe("Could not read from file." + e);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("inventory", inventory);

		RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			util.Files.LOGGER.severe("Servlet Exception: Could not forward data to index.jsp." + e);
		} catch (IOException e) {
			util.Files.LOGGER.severe("IO Exception: Could not forward data to index.jsp." + e);
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
