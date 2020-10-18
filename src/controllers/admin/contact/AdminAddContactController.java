package controllers.admin.contact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;
import models.Contact;

public class AdminAddContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ContactDAO contactDAO = new ContactDAO();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		Contact contact = new Contact(0, name, email, website, message);
		request.setAttribute("contact", contact);
		
		if(!contactDAO.checkEmail(email)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/add.jsp?msg=2");
			rd.forward(request, response);
			return;
		}

		int result = contactDAO.addItem(contact);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=1");
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/add.jsp?msg=1");
			rd.forward(request, response);
		}
	}

}
