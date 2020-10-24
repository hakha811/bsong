package controllers.admin.picture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PictureDAO;
import models.Picture;
import utils.FileUtil;

public class AdminDelPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PictureDAO pictureDAO = null;

	public AdminDelPictureController() {
		super();
		pictureDAO = new PictureDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/picture?msg=0");
			return;
		}
		Picture picture = pictureDAO.getItemById(id);
		int result = pictureDAO.deleteItem(id);
		if (result > 0) {
			FileUtil.deleteImage(picture.getName(), request);
			response.sendRedirect(request.getContextPath() + "/admin/picture?msg=3");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/picture?msg=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
