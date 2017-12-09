package dao;

import java.sql.*;

public class MariaDB {


	public Connection Get_Connection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/sweep";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "root");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
		
}
