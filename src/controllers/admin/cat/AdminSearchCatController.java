package controllers.admin.cat;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import models.Category;

public class AdminSearchCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDAO catDAO = null;

	public AdminSearchCatController() {
		super();
		catDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/cat");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		List<Category> listCat = catDAO.getItemsByName(name);

		request.setAttribute("listCat", listCat);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);
	}

}
