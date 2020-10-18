package controllers.admin.contact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;
import models.Contact;

public class AdminEditContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDAO contactDAO = null;

	public AdminEditContactController() {
		super();
		contactDAO = new ContactDAO();
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
		Contact contact = contactDAO.getItemById(id);
		request.setAttribute("contact", contact);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		Contact contact = new Contact(id, name, email, website, message);
		request.setAttribute("contact", contact);
		int result = contactDAO.updateItem(contact);

		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/contact?msg=2");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/contact?msg=0");
		}
	}

}
