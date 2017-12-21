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
import dto.location_basicObj;;

@Path("/Utility")
public class Utility {

	@GET
	@Path("/GetLocation")
	@Produces("application/json")
	public String getAll() {
		String locations = null;
		try {
			ArrayList<location_basicObj> locationData = null;
			ProjectManager projectManager = new ProjectManager();
			locationData = projectManager.GetLocations();
			Gson gson = new Gson();
			//System.out.println(gson.toJson(locationData));
			locations = gson.toJson(locationData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return locations;
	}
	
	

}
