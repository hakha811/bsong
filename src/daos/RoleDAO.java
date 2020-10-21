package daos;

import java.sql.SQLException;

import models.Role;
import utils.DBConnectionUtil;

public class RoleDAO extends AbstractDAO {

	public Role getItemById(int id) {
		Role role = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT id, name FROM roles WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				role = new Role(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return role;
	}

	public String getRole(int id) {
		String name = "";
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT name FROM roles WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return name;
	}

	public int addItem(Role role) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO roles (id, name) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, role.getId());
			pst.setString(2, role.getName());
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
		final String sql = "DELETE FROM roles WHERE id = ?";
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

	public int updateItem(Role role) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE roles SET name = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, role.getName());
			pst.setInt(2, role.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}
}
