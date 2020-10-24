package controllers.admin.contact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;
import models.Contact;
import utils.DefineUtil;

public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDAO contactDAO = null;

	public AdminIndexContactController() {
		super();
		contactDAO = new ContactDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;

		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int numOfItems = contactDAO.countItems();
		int numOfPages = (int) Math.ceil((float) numOfItems / DefineUtil.NUMBER_PER_PAGE);

		if (currentPage > numOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offSet = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

		List<Contact> listContact = contactDAO.getItems(offSet, DefineUtil.NUMBER_PER_PAGE);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listContact", listContact);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
