package controllers.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDAO;
import models.Song;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicSearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		String search = (String)request.getParameter("editbox_search");
		List<Song> listSongSearch = songDAO.getItemByName(search);
		if(listSongSearch != null && listSongSearch.size()>0) {
			request.setAttribute("listSong", listSongSearch);
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/search.jsp");
			rd.forward(request, response);
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/search.jsp?msg=notfound)");
			rd.forward(request, response);
		}
	}

}
