package controllers.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.RoleDAO;
import daos.UserDAO;
import models.Role;
import models.User;
import utils.StringUtil;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;
	RoleDAO roleDAO = null;

	public RegisterController() {
		super();
		userDAO = new UserDAO();
		roleDAO = new RoleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/register.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		String repassword = StringUtil.md5(request.getParameter("repassword"));
		HttpSession session = request.getSession();
		User user = new User(0, username, "", name);
		request.setAttribute("user", user);

		if (!password.equals(repassword)) {
			response.sendRedirect(request.getContextPath() + "/auth/register?msg=1");
			return;
		}
		if (!userDAO.checkUsername(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/auth/register.jsp?msg=2");
			rd.forward(request, response);
			return;
		}

		user.setPassword(password);
		int result = userDAO.addItem(user);
		if (result > 0) {
			int id = userDAO.getIdByName(username);
			Role role = new Role(id, "editor");
			roleDAO.addItem(role);
			session.setAttribute("username", username);
			session.setAttribute("role", role);
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			response.sendRedirect(request.getContextPath() + "/auth/register?msg=0");
		}
	}

}
