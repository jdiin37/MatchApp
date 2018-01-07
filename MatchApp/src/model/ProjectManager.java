package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

import dao.MariaDB;
import dao.Project;
import dto.user_basicObj;
import dto.location_basicObj;
import dto.post_basicObj;
import dto.js_postQueryObj;
import dto.img_basicObj;

public class ProjectManager {
	public ArrayList<location_basicObj> GetLocations() throws Exception {
		ArrayList<location_basicObj> locations = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			locations = project.GetLocations(connection);
		} catch (Exception e) {
			throw e;
		}
		return locations;
	}
	
	public ArrayList<img_basicObj> GetImgs(List<img_basicObj> postIds) throws Exception {
		ArrayList<img_basicObj> imgs = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			imgs = project.GetImgs(connection,postIds);
		} catch (Exception e) {
			throw e;
		}
		return imgs;
	}
	
	
	public ArrayList<user_basicObj> GetUsers() throws Exception {
		ArrayList<user_basicObj> users = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			users = project.GetUsers(connection);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}
	
	public user_basicObj IsUser(user_basicObj userObj) throws Exception {
		user_basicObj users = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			users = project.IsUser(connection,userObj);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}
	
	public user_basicObj forgetUser(user_basicObj userObj) throws Exception {
		user_basicObj users = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			users = project.forgetUser(connection,userObj);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	
	public user_basicObj CreateUser(user_basicObj userObj) throws Exception {
		user_basicObj users = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			users = project.CreateUser(connection,userObj);
		} catch (Exception e) {
			throw e;
		}
		return users;
	}
	
	public post_basicObj CreatePost(post_basicObj postObj) throws Exception {
		post_basicObj post = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			post = project.CreatePost(connection,postObj);
		} catch (Exception e) {
			throw e;
		}
		return post;
	}
	
	public ArrayList<post_basicObj> GetPosts(js_postQueryObj queryObj) throws Exception {
		ArrayList<post_basicObj> posts = null;
		try {
			MariaDB database = new MariaDB();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			posts = project.GetPosts(connection,queryObj);
		} catch (Exception e) {
			throw e;
		}
		return posts;
	}
}
