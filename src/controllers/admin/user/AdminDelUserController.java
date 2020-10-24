package controllers.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.RoleDAO;
import daos.UserDAO;
import models.User;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;
	RoleDAO roleDAO = null;

	public AdminDelUserController() {
		super();
		userDAO = new UserDAO();
		roleDAO = new RoleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		User user = userDAO.getItemById(id);
		int result = userDAO.deleteItem(id);
		if (result > 0) {
			roleDAO.deleteItem(id);
			if(user.getUsername().equals(session.getAttribute("username"))) {
				response.sendRedirect(request.getContextPath() + "/auth/logout");
				return;
			}
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
