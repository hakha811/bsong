package controllers.admin.contact;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;

public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactDAO contactDAO = new ContactDAO();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		int result = contactDAO.deleteItem(id);
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/contact?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/contact?msg=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
