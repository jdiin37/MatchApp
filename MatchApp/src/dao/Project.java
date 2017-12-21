package dao;

import java.sql.*;
import java.util.ArrayList;
import dto.user_basicObj;
import dto.location_basicObj;
import dto.post_basicObj;

public class Project {
	public ArrayList<location_basicObj> GetLocations(Connection connection) throws Exception {
		ArrayList<location_basicObj> location_basicData = new ArrayList<location_basicObj>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id,location FROM location_basic order by location");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				location_basicObj Object = new location_basicObj();
				Object.setId(rs.getInt("id"));
				Object.setLocation(rs.getString("location"));
				location_basicData.add(Object);
			}
			return location_basicData;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
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

	public user_basicObj IsUser(Connection connection, user_basicObj userObj) throws Exception {
		user_basicObj Object = new user_basicObj();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT user_id,password,auth,email FROM user_basic Where user_id = ? and password = ?");
			ps.setString(1, userObj.getUser_id());
			ps.setString(2, userObj.getPassword());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				Object.setUser_id(rs.getString("user_id"));
				Object.setPassword("****");
				Object.setAuth(rs.getString("auth"));
				Object.setEmail(rs.getString("email"));
			}
			return Object;
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

	
	public post_basicObj CreatePost(Connection connection, post_basicObj postObj) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO post_basic (user_id, location, location_desc,demand_desc,fee,cre_date) VALUES (?, ?, ?, ?, ?, ?)", new String[] { "ID" });
			ps.setString(1, postObj.getUser_id());
			ps.setString(2, postObj.getLocation());
			ps.setString(3, postObj.getLocation_desc());
			ps.setString(4, postObj.getDemand_desc());
			ps.setInt(5, postObj.getFee());
			ps.setString(6, postObj.getCre_date());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			// Update the id in the returned object. This is important as this value must be
			// returned to the client.
			int id = rs.getInt(1);
			postObj.setId(id);
		} catch (Exception e) {
			throw e;
		}
		return postObj;

	}
}
