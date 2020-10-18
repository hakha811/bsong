package controllers.publics;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;
import models.Contact;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactDAO contactDAO = new ContactDAO();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("website", website);
		request.setAttribute("message", message);
		
		if(!contactDAO.checkEmail(email)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp?msg=1");
			rd.forward(request, response);
			return;
		}

		int check = contactDAO.addItem(new Contact(0, name, email, website, message));
		if (check >= 1) {
			response.sendRedirect(request.getContextPath() + "/contact?msg=0");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp?msg=1");
			rd.forward(request, response);
			return;
		}

	}

}
