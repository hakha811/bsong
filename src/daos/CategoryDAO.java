package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import utils.DBConnectionUtil;

public class CategoryDAO extends AbstractDAO {

	public List<Category> getAll() {
		List<Category> list = new ArrayList<Category>();
		final String sql = "SELECT id,name FROM categories";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category cat = new Category(rs.getInt("id"), rs.getString("name"));
				list.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, st);
		}
		return list;
	}
	
	public List<Category> getItems(int begin, int end) {
		List<Category> list = new ArrayList<Category>();
		final String sql = "SELECT id,name FROM categories ORDER BY id DESC LIMIT ?, ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, begin);
			pst.setInt(2, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				Category cat = new Category(rs.getInt("id"), rs.getString("name"));
				list.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public Category getItemById(int id) {
		Category cat = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT id, name FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				cat = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return cat;
	}
	
	public Category getItemByName(String name) {
		Category cat = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT id, name FROM categories WHERE name = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				cat = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return cat;
	}

	public List<Category> getItemsByName(String name) {
		List<Category> list = new ArrayList<Category>();
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM categories WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				Category cat = new Category(rs.getInt("id"), rs.getString("name"));
				list.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addItem(Category cat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO categories (name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
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
		final String sql = "DELETE FROM categories WHERE id = ?";
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

	public int updateItem(Category cat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE categories SET name = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
			pst.setInt(2, cat.getId());
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
		final String sql = "SELECT COUNT(*) FROM categories";
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
	
	public boolean checkName(String name) {
		boolean result = true;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM categories WHERE name = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
