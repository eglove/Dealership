package servlets;

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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	ArrayList<Vehicle> searchResults = (ArrayList<Vehicle>) util.Files.searchFileForVehicle(util.Files.INVENTORYFILE, request.getParameter("search"));
    	HttpSession session = request.getSession(true);
    	session.setAttribute("inventory", searchResults);
    	
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
