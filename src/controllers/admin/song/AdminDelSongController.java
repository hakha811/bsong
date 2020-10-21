package controllers.admin.song;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDAO;
import models.Song;
import utils.FileUtil;

public class AdminDelSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO = null;

	public AdminDelSongController() {
		super();
		songDAO = new SongDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/song?msg=0");
			return;
		}
		Song song = songDAO.getItemById(id);
		int result = songDAO.deleteItem(id);
		FileUtil.deleteImage(song.getPicture(), request);
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/song?msg=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
