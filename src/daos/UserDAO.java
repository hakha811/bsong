package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import utils.DBConnectionUtil;

public class UserDAO extends AbstractDAO {
	public List<User> getAll() {
		List<User> list = new ArrayList<User>();
		final String sql = "SELECT id,username,fullname FROM users ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, st);
		}
		return list;
	}

	public List<User> getItems(int begin, int end) {
		List<User> list = new ArrayList<User>();
		final String sql = "SELECT id,username,fullname FROM users ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, begin);
			pst.setInt(2, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public User getItemById(int id) {
		User user = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM users WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return user;
	}

	public int getIdByName(String username) {
		int id = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT id FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return id;
	}

	public List<User> getItemByName(String name) {
		List<User> list = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM users WHERE fullname LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, "%" + name + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addItem(User user) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO users(username, password, fullname) VALUES(?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
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
		final String sql = "DELETE FROM users WHERE id = ?";
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

	public int updateItem(User user) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE users SET username = ?, fullname = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getFullname());
			pst.setInt(3, user.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}

	public User isUser(String username, String password) {
		User user = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT id, username, fullname FROM users WHERE username = ? AND password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean checkUsername(String username) {
		boolean result = true;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countItems() {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT COUNT(*) FROM users";
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
