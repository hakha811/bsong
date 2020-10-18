package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import constants.Constants;

public class DBConnectionUtil {
	private static Connection conn;

	public static Connection getConnection() {
		try {
			Class.forName(Constants.DRIVER);
			conn = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, Statement st) {
		close(conn);
		close(st);
	}
	
	public static void close(Connection conn, PreparedStatement pst) {
		close(conn);
		close(pst);
	}

	public static void close(Connection conn, ResultSet rs, Statement st) {
		close(conn);
		close(rs);
		close(st);
	}

	public static void close(Connection conn, ResultSet rs, PreparedStatement pst) {
		close(conn);
		close(rs);
		close(pst);
	}

	public static void close(Connection conn, ResultSet rs, Statement st, PreparedStatement pst) {
		close(conn);
		close(rs);
		close(st);
		close(pst);
	}
}
