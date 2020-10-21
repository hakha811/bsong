package controllers.admin.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.RoleDAO;
import daos.UserDAO;
import models.Role;
import models.User;
import utils.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;
	RoleDAO roleDAO = null;

	public AdminAddUserController() {
		super();
		userDAO = new UserDAO();
		roleDAO = new RoleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String role = request.getParameter("role");
		User user = new User(0, username, "", name);
		request.setAttribute("user", user);

		if (!password.equals(repassword)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		} else if (!userDAO.checkUsername(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?msg=1");
			rd.forward(request, response);
			return;
		}

		user = new User(0, username, StringUtil.md5(password), name);
		int result = userDAO.addItem(user);

		if (result > 0) {
			int id = userDAO.getIdByName(username);
			roleDAO.addItem(new Role(id, role));

			response.sendRedirect(request.getContextPath() + "/admin/user?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?msg=2");
			rd.forward(request, response);
			return;
		}
	}

}
