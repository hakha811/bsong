package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import daos.SongDAO;
import daos.UserDAO;

public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDAO catDAO = null;
	SongDAO songDAO = null;
	UserDAO userDAO = null;

	public AdminIndexController() {
		super();
		catDAO = new CategoryDAO();
		songDAO = new SongDAO();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("countCat", catDAO.countItems());
		request.setAttribute("countSong", songDAO.countItems());
		request.setAttribute("countUser", userDAO.countItems());

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
