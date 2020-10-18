package controllers.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import daos.SongDAO;
import models.Category;
import models.Song;
import utils.DefineUtil;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO;
	CategoryDAO catDAO;

	public PublicCatController() {
		super();
		songDAO = new SongDAO();
		catDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		int currentPage = 1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}

		Category cat = catDAO.getItemById(id);
		if (cat == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		int numOfItems = songDAO.countItemsByCatId(id);
		int numOfPages = (int) Math.ceil((float) numOfItems / DefineUtil.NUMBER_PER_PAGE);

		if (currentPage > numOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offSet = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

		List<Song> listSong = songDAO.getItemsByCatId(id, offSet);

		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cat", cat);
		request.setAttribute("listSong", listSong);

		RequestDispatcher rd = request.getRequestDispatcher("/views/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
