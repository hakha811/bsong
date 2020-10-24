package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Picture;
import utils.DBConnectionUtil;

public class PictureDAO extends AbstractDAO {
	public List<Picture> getAll() {
		List<Picture> list = new ArrayList<Picture>();
		final String sql = "SELECT * FROM pictures ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Picture picture = new Picture(rs.getInt("id"), rs.getString("name"));
				list.add(picture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, st);
		}
		return list;
	}

	public List<Picture> getItems(int begin, int end) {
		List<Picture> list = new ArrayList<Picture>();
		final String sql = "SELECT * FROM pictures ORDER BY id DESC LIMIT ?, ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, begin);
			pst.setInt(2, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				Picture picture = new Picture(rs.getInt("id"), rs.getString("name"));
				list.add(picture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public Picture getItemById(int id) {
		Picture picture = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM pictures WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				picture = new Picture(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return picture;
	}

	public List<Picture> getItemsByName(String name) {
		List<Picture> list = new ArrayList<Picture>();
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM pictures WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				Picture picture = new Picture(rs.getInt("id"), rs.getString("name"));
				list.add(picture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addItem(Picture picture) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO pictures (name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, picture.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}

	public int deleteItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "DELETE FROM pictures WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}

	public int updateItem(Picture picture) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE pictures SET name = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, picture.getName());
			pst.setInt(2, picture.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}

	public int countItems() {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT COUNT(*) FROM pictures";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, st);
		}
		return result;
	}
}
