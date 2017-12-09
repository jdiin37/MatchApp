package dao;

import java.sql.*;
import java.util.ArrayList;
import dto.user_basicObj;

public class Project {
	public ArrayList<user_basicObj> GetUsers(Connection connection) throws Exception {
		ArrayList<user_basicObj> user_basicData = new ArrayList<user_basicObj>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT user_id,password,auth,email FROM user_basic");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user_basicObj Object = new user_basicObj();
				Object.setUser_id(rs.getString("user_id"));
				Object.setPassword(rs.getString("password"));
				Object.setAuth(rs.getString("auth"));
				Object.setEmail(rs.getString("email"));
				user_basicData.add(Object);
			}
			return user_basicData;
		} catch (Exception e) {
			throw e;
		}
	}

	public user_basicObj CreateUser(Connection connection, user_basicObj userObj) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO user_basic (user_id, password, email,cre_date) VALUES (?, ?, ?, ?)", new String[] { "ID" });
			ps.setString(1, userObj.getUser_id());
			ps.setString(2, userObj.getPassword());
			ps.setString(3, userObj.getEmail());
			ps.setString(4, userObj.getCre_date());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			// Update the id in the returned object. This is important as this value must be
			// returned to the client.
			int id = rs.getInt(1);
			userObj.setId(id);
		} catch (Exception e) {
			throw e;
		}
		return userObj;

	}

}
