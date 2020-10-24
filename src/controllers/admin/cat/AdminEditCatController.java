package controllers.admin.cat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import models.Category;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDAO catDAO = null;

	public AdminEditCatController() {
		super();
		catDAO = new CategoryDAO();
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
		Category cat = catDAO.getItemById(id);
		request.setAttribute("cat", cat);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		Category cat = new Category(id, name);
		int result = catDAO.updateItem(cat);

		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat?id="+id+"&msg=0");
		}
	}

}
