package WebAPI;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.ProjectManager;
import com.google.gson.Gson;
import dto.user_basicObj;

@Path("/User")
public class User {

	@GET
	@Path("/GetUser")
	@Produces("application/json")
	public String getAll() {
		String users = null;
		try {
			ArrayList<user_basicObj> userData = null;
			ProjectManager projectManager = new ProjectManager();
			userData = projectManager.GetUsers();
			Gson gson = new Gson();
			System.out.println(gson.toJson(userData));
			users = gson.toJson(userData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return users;
	}
	
	@POST 
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/Login")
	public String Login(user_basicObj cre_user) {
		String users = null;

		try {
			//user_basicObj newUser = new Gson().fromJson(cre_user, user_basicObj.class);
			user_basicObj newUser = cre_user;
			ProjectManager projectManager = new ProjectManager();
			newUser = projectManager.IsUser(newUser);
			Gson gson = new Gson();
			System.out.println(gson.toJson(newUser));
			users = gson.toJson(newUser);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return users;
	}
	
	@POST 
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/Create")
	public String Create(user_basicObj cre_user) {
		String users = null;

		try {
			//user_basicObj newUser = new Gson().fromJson(cre_user, user_basicObj.class);
			user_basicObj newUser = cre_user;
			ProjectManager projectManager = new ProjectManager();
			newUser = projectManager.CreateUser(newUser);
			Gson gson = new Gson();
			System.out.println(gson.toJson(newUser));
			users = gson.toJson(newUser);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return users;
	}

}
