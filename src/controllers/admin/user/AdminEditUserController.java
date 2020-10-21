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

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;
	RoleDAO roleDAO = null;

	public AdminEditUserController() {
		super();
		userDAO = new UserDAO();
		roleDAO = new RoleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		User user = userDAO.getItemById(id);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		String role = roleDAO.getRole(user.getId());
		request.setAttribute("user", user);
		request.setAttribute("role", role);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		User user = new User(id, "", "", name);
		int result = userDAO.updateItem(user);

		if (result > 0) {
			roleDAO.updateItem(new Role(id, role));
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user?id="+id+"&msg=0");
		}
	}

}
