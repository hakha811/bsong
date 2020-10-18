package controllers.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDAO;
import models.User;
import utils.StringUtil;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;

	public LoginController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") != null) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		HttpSession session = request.getSession();

		User user = userDAO.isUser(username, password);
		if (user != null) {
			session.setAttribute("username", username);
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}

		response.sendRedirect(request.getContextPath() + "/auth/login?msg=0");
	}

}
