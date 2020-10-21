package controllers.admin.song;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import daos.SongDAO;
import models.Category;
import models.Song;
import utils.FileUtil;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDAO catDAO = null;
	SongDAO songDAO = null;

	public AdminAddSongController() {
		super();
		catDAO = new CategoryDAO();
		songDAO = new SongDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> listCat = (List<Category>) catDAO.getAll();
		request.setAttribute("listCat", listCat);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		int cat_id = Integer.parseInt(request.getParameter("category"));
		Song song = new Song(0, name, preview, detail, null, "", 0, new Category(cat_id, ""));

		String fileName = FileUtil.uploadImage("picture", request);
		song.setPicture(fileName);

		int result = songDAO.addItem(song);

		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song?msg=1");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
