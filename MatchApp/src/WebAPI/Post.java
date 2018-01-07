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
import dto.post_basicObj;
import dto.user_basicObj;
import dto.js_postQueryObj;

@Path("/Post")
public class Post {
	
	@POST 
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/CrePost")
	public String Create(post_basicObj cre_post) {
		String post = null;

		try {
			post_basicObj newPost = cre_post;
			ProjectManager projectManager = new ProjectManager();
			newPost = projectManager.CreatePost(newPost);
			Gson gson = new Gson();
			System.out.println(gson.toJson(newPost));
			post = gson.toJson(newPost);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return post;
	}
	
	@POST 
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/GetPosts")
	public String Get(js_postQueryObj queryObj) {
		String posts = null;

		try {
			ArrayList<post_basicObj> postList = null;
			ProjectManager projectManager = new ProjectManager();
			postList = projectManager.GetPosts(queryObj);
			Gson gson = new Gson();
			//System.out.println(gson.toJson(postList));
			posts = gson.toJson(postList);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return posts;
	}

}
