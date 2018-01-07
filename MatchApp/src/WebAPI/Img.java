package WebAPI;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.ProjectManager;
import com.google.gson.Gson;

import dto.img_basicObj;

@Path("/Img")
public class Img {
	
	
	@POST 
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/GetImg")
	public String Get(List<img_basicObj> PostIds) {
		String imgs = null;
		try {
			ArrayList<img_basicObj> imgList = null;
			ProjectManager projectManager = new ProjectManager();
			imgList = projectManager.GetImgs(PostIds);
			Gson gson = new Gson();
			//System.out.println(gson.toJson(imgList));
			imgs = gson.toJson(imgList);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return imgs;
	}

}
