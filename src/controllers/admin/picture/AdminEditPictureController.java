package controllers.admin.picture;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.PictureDAO;
import models.Picture;
import utils.FileUtil;

@MultipartConfig
public class AdminEditPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PictureDAO pictureDAO = null;

	public AdminEditPictureController() {
		super();
		pictureDAO = new PictureDAO();
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
		Picture picture = pictureDAO.getItemById(id);
		if (picture == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		request.setAttribute("picture", picture);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/picture/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		Picture picture = pictureDAO.getItemById(id);
		String fileName = FileUtil.uploadImage("picture", request);
		if ("".equals(fileName)) {
			response.sendRedirect(request.getContextPath() + "/admin/picture/edit.jsp?id="+id+"&msg=1");
			return;
		}
		picture.setName(fileName);

		int result = pictureDAO.updateItem(picture);
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/song?msg=2");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user?id="+id+"&msg=0");
		}
	}

}
