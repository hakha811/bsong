package daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Song;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class SongDAO extends AbstractDAO {
	public List<Song> getAll() {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT songs.*, categories.name AS catName "
				+ "FROM songs INNER JOIN categories ON songs.cat_id = categories.id " 
				+ "ORDER BY songs.id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), rs.getString("catName")));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, st);
		}
		return list;
	}

	public List<Song> getItemsByCatId(int cat_id) {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs WHERE cat_id = ? ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public List<Song> getItemsByCatId(int cat_id, int begin) {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs WHERE cat_id = ? ORDER BY id DESC LIMIT ?, ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			pst.setInt(2, begin);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public List<Song> getItemsByCatId(int cat_id, int id, int begin) {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs WHERE cat_id = ? AND id <> ? ORDER BY id DESC LIMIT ?, ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			pst.setInt(2, id);
			pst.setInt(3, begin);
			pst.setInt(4, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public List<Song> getItems(int limit) {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs ORDER BY id DESC LIMIT ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, limit);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public List<Song> getItems(int begin, int end) {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs ORDER BY id DESC LIMIT ?, ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, begin);
			pst.setInt(2, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public Song getItemById(int id) {
		Song song = null;
		final String sql = "SELECT * FROM songs WHERE id = ?";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return song;
	}

	public List<Song> getItemByName(String name) {
		List<Song> list = new ArrayList<Song>();
		final String sql = "SELECT * FROM songs WHERE name LIKE ? ORDER BY id DESC";
		conn = DBConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setNString(1, "%" + name + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("preview_text"),
						rs.getString("detail_text"), 
						Timestamp.valueOf(rs.getString("date_create")),
						rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), ""));
				list.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return list;
	}

	public int addItem(Song song) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "INSERT INTO songs(name, preview_text, detail_text, picture, counter, cat_id) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getCounter());
			pst.setInt(6, song.getCat().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst);
		}
		return result;
	}

	public int updateItem(Song song) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE songs SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getCat().getId());
			pst.setInt(6, song.getId());
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
		final String sql = "DELETE FROM songs WHERE id = ?";
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

	public int countItems() {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT COUNT(*) FROM songs";
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

	public int countItemsByCatId(int cat_id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "SELECT COUNT(*) FROM songs WHERE cat_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return result;
	}

	public int updateView(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		final String sql = "UPDATE songs SET counter = counter + 1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, rs, pst);
		}
		return result;
	}
}
