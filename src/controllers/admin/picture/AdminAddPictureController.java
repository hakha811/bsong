package controllers.admin.picture;

import java.io.IOException;
import java.util.List;

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
public class AdminAddPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PictureDAO pictureDAO = null;

	public AdminAddPictureController() {
		super();
		pictureDAO = new PictureDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Picture> listPicture = (List<Picture>) pictureDAO.getAll();
		request.setAttribute("listPicture", listPicture);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/picture/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fileName = FileUtil.uploadImage("picture", request);
		if ("".equals(fileName)) {
			response.sendRedirect(request.getContextPath() + "/admin/picture/add.jsp?msg=0");
			return;
		}

		Picture picture = new Picture(0, fileName);
		int result = pictureDAO.addItem(picture);
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/picture?msg=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/picture/add.jsp?msg=0");
		}
	}

}
