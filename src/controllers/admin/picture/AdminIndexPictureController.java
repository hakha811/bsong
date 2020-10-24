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
import utils.DefineUtil;

public class AdminIndexPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PictureDAO pictureDAO = null;

	public AdminIndexPictureController() {
		super();
		pictureDAO = new PictureDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;

		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int numOfItems = pictureDAO.countItems();
		int numOfPages = (int) Math.ceil((float) numOfItems / DefineUtil.NUMBER_PER_PAGE);

		if (currentPage > numOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offSet = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

		List<Picture> listPicture = pictureDAO.getItems(offSet, DefineUtil.NUMBER_PER_PAGE);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listPicture", listPicture);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/picture/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
