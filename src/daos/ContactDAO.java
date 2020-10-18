package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Contact;
import utils.DBConnectionUtil;

public class ContactDAO extends AbstractDAO {
	public List<Contact> getAll() {
		List<Contact> list = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM contacts";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
				;
				list.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, st);
		}
		return list;
	}

	public Contact getItemById(int id) {
		Contact contact = null;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM contacts WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return contact;
	}

	public List<Contact> getItemByName(String name) {
		List<Contact> list = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM contacts WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, "%" + name + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				Contact contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("website"), rs.getString("message"));
				list.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addItem(Contact contact) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO contacts(name, email, website, message) VALUES (?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
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
		final String sql = "DELETE FROM contacts WHERE id = ?";
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

	public int updateItem(Contact contact) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE contacts SET name = ?, email = ?, website = ?, message = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
			pst.setInt(5, contact.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}
	
	public boolean checkEmail(String email) {
		boolean result = true;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT * FROM contacts WHERE email = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
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
