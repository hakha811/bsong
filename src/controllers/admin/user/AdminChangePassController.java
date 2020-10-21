package controllers.admin.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UserDAO;
import models.User;
import utils.StringUtil;

public class AdminChangePassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;

	public AdminChangePassController() {
		super();
		userDAO = new UserDAO();
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

		request.setAttribute("user", user);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/changepass.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String password = StringUtil.md5(request.getParameter("password"));
		String newpassword = StringUtil.md5(request.getParameter("newpassword"));
		String repassword = StringUtil.md5(request.getParameter("repassword"));
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		User user = userDAO.isUser(userDAO.getItemById(id).getUsername(), password);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/admin/user/changepass?id=" + id + "&msg=2");
			return;
		}
		if (!newpassword.equals(repassword)) {
			response.sendRedirect(request.getContextPath() + "/admin/user/changepass?id=" + id + "&msg=1");
			return;
		}

		user.setPassword(newpassword);	
		int result = userDAO.updateItem(user);
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user/changepass?id=\"+id+\"&msg=0");
		}
	}

}
