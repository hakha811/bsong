package controllers.admin.song;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDAO;
import models.Song;
import utils.DefineUtil;

public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO = null;

	public AdminIndexSongController() {
		super();
		songDAO = new SongDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;

		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}

		int numOfItems = songDAO.countItems();
		int numOfPages = (int) Math.ceil((float) numOfItems / DefineUtil.NUMBER_PER_PAGE);

		if (currentPage > numOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offSet = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;

		List<Song> listSong = songDAO.getItems(offSet, DefineUtil.NUMBER_PER_PAGE);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listSong", listSong);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
