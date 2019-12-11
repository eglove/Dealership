package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			util.Files.LOGGER.severe("Could not redirect to login.jsp." + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			if (!(username.equals("admin") && password.equals("pass"))) {
				request.getSession().setAttribute("msg", "<span style=\"color: red;\">Invalid Credentials</span>");
				response.sendRedirect("login.jsp");
			} else {
				response.sendRedirect("admin.jsp");
			}
		} catch (IOException e) {
			util.Files.LOGGER.severe("IOException logging in." + e);
		}
	}
}
