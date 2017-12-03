package WebAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/member")
public class member {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHi(){
		String response = "{name:123}";
		
		return response;
		
	}
	

}
