package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {

	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return false;
		}
		return true;
	}

	public static int checkRole(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("role") == null) {
			return 0;
		} else if (session.getAttribute("role").equals("editor")) {
			return 1;
		} else if (session.getAttribute("role").equals("admin")) {
			return 2;
		}
		return 0;
	}
}
