package model;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MariaDB;
import dao.Project;
import dto.user_basicObj;
import dto.location_basicObj;

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
	
}
