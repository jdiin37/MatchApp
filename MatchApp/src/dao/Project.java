package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

import dto.user_basicObj;
import dto.location_basicObj;
import dto.post_basicObj;
import dto.js_postQueryObj;
import dto.img_basicObj;

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
	
	public ArrayList<img_basicObj> GetImgs(Connection connection,List<img_basicObj> postIds) throws Exception {
		ArrayList<img_basicObj> imgs = new ArrayList<img_basicObj>();
		String inClause = "";
		for(int i = 0;i < postIds.size(); i++){
			if (i == postIds.size() - 1) {
				inClause += postIds.get(i).getPost_id();
			}else {
				inClause += postIds.get(i).getPost_id() + ",";
			}
		}
		//System.out.println(inClause);
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id,post_id,img FROM img_basic Where post_id In (" +inClause +")order by id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				img_basicObj Object = new img_basicObj();
				Object.setId(rs.getInt("id"));
				Object.setPost_id(rs.getInt("post_id"));
				Object.setImg(rs.getBlob("img"));
				imgs.add(Object);
			}
			return imgs;
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
	
	public user_basicObj forgetUser(Connection connection, user_basicObj userObj) throws Exception {
		user_basicObj Object = new user_basicObj();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT user_id,password,auth,email FROM user_basic Where email = ? ");
			ps.setString(1, userObj.getEmail());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				Object.setUser_id(rs.getString("user_id"));
				Object.setPassword(rs.getString("password"));
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
			userObj.setServerMsg("OK");
		} catch (Exception e) {
			userObj.setServerMsg(e.getMessage().toString());
			System.out.println(e.getMessage());
			//throw e;
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
	
	public ArrayList<post_basicObj> GetPosts(Connection connection,js_postQueryObj queryObj) throws Exception {
		ArrayList<post_basicObj> postList = new ArrayList<post_basicObj>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id,user_id,location,location_desc,demand_desc,fee,cre_date,mod_date FROM post_basic order by id desc LIMIT ? ");
			ps.setInt(1, queryObj.getRowNumber());
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				post_basicObj Object = new post_basicObj();
				Object.setId(rs.getInt("id"));
				Object.setUser_id(rs.getString("user_id"));
				Object.setLocation(rs.getString("location"));
				Object.setLocation_desc(rs.getString("location_desc"));
				Object.setDemand_desc(rs.getString("demand_desc"));
				Object.setFee(rs.getInt("fee"));
				Object.setCre_date(rs.getString("cre_date"));
				Object.setMod_date(rs.getString("mod_date"));
				postList.add(Object);
			}
			return postList;
		} catch (Exception e) {
			throw e;
		}
	}
}
