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

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO;
	CategoryDAO catDAO;

	public PublicDetailController() {
		super();
		songDAO = new SongDAO();
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

		Song song = songDAO.getItemById(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		Category cat = catDAO.getItemById(song.getCat().getId());
		List<Song> listSongRelated = songDAO.getItemsByCatId(song.getCat().getId(), song.getId(), 0);
		songDAO.updateView(song.getId());

		request.setAttribute("cat", cat);
		request.setAttribute("song", song);
		request.setAttribute("listSongRelated", listSongRelated);

		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
