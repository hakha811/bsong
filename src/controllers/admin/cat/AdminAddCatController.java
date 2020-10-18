package controllers.admin.cat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import models.Category;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CategoryDAO catDAO = new CategoryDAO();
		String name = request.getParameter("name");
		Category cat = new Category(0, name);
		request.setAttribute("cat", cat);
		
		if(!catDAO.checkName(name)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp?msg=2");
			rd.forward(request, response);
			return;
		}

		int result = catDAO.addItem(cat);
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat?msg=1");
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp?msg=1");
			rd.forward(request, response);
		}
	}

}
