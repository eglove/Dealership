package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Address;
import objects.Person;
import objects.Transaction;

/**
 * Servlet implementation class Transactions
 */
@WebServlet("/Transactions")
public class Transactions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Transactions() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Transaction> ledger = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(util.Files.LEDGERFILE))) {
			String line;
			String[] items = null;
			while ((line = reader.readLine()) != null) {
				items = line.split(",");
				ledger.add(new Transaction(items[0], items[1], new Person(items[2], items[3], items[4], items[5],
						new Address(items[6], items[7], items[8], items[9]))));
			}
		} catch (IOException e) {
			util.Files.LOGGER.severe("Could not read from file." + e);
		}

		Collections.reverse(ledger);
		HttpSession session = request.getSession(true);
		session.setAttribute("ledger", ledger);

		RequestDispatcher rs = request.getRequestDispatcher("transactions.jsp");

		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			util.Files.LOGGER.severe("Servlet Exception: Could not forward data." + e);
		} catch (IOException e) {
			util.Files.LOGGER.severe("IO Exception: Could not forward data." + e);
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
