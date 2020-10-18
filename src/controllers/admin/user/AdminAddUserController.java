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

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		User user = new User(0, username, "", name);
		request.setAttribute("user", user);
		
		if(!password.equals(repassword)) {
			request.setAttribute("msg", "Mật khẩu không trùng nhau!!!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		else if(!userDAO.checkUsername(username)) {
			request.setAttribute("msg", "Tên tài khoản đã tồn tại");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}

		user = new User(0, username, StringUtil.md5(password), name);
		int result = userDAO.addItem(user);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=1");
			return;
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
