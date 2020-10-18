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
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO catDAO = new CategoryDAO();
		List<Category> listCat = (List<Category>) catDAO.getAll();
		request.setAttribute("listCat", listCat);
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		SongDAO songDAO = new SongDAO();
		Song song = songDAO.getItemById(id);
		request.setAttribute("song", song);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		CategoryDAO catDAO = new CategoryDAO();
		List<Category> listCat = (List<Category>) catDAO.getAll();
		request.setAttribute("listCat", listCat);
		
		SongDAO songDAO = new SongDAO();
		int id = 0;
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		int cat_id = Integer.parseInt(request.getParameter("category"));
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		Song song = songDAO.getItemById(id);
		song.setName(name);
		song.setPreview_text(preview);
		song.setDetail_text(detail);
		song.setCat(new Category(cat_id, ""));

		String fileName = FileUtil.uploadImage("picture", request);
		if(!"".equals(fileName)) {
			song.setPicture(fileName);
		}

		request.setAttribute("song", song);
		int result = songDAO.updateItem(song);
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song?msg=2");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/song?msg=0");
			return;
		}
	}

}
