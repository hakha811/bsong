package controllers.admin.picture;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PictureDAO;
import models.Picture;

public class AdminSearchPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PictureDAO pictureDAO = null;

	public AdminSearchPictureController() {
		super();
		pictureDAO = new PictureDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/song");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		List<Picture> listPicture = pictureDAO.getItemsByName(name);

		request.setAttribute("listPicture", listPicture);
		request.setAttribute("search", true);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/picture/index.jsp");
		rd.forward(request, response);
	}

}
