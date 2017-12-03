package WebAPI;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/basic")
public class basic {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHi(){
		String response = "basic";
		
		return response;
		
	}
}
