package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class AbstractDAO {
	protected Connection conn;
	protected Statement st;
	protected ResultSet rs;
	protected PreparedStatement pst;
}
