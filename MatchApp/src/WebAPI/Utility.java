package WebAPI;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.ProjectManager;
import com.google.gson.Gson;
import dto.location_basicObj;
import dto.user_basicObj;;

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
	
	public static void SendMail (user_basicObj userObj,Integer mailFlag) {    //mailFlag,0:welcome mail 1:forget mail
	      // Recipient's email ID needs to be mentioned.
	      String to = userObj.getEmail();
	      String host = "smtp.gmail.com";
	      int port = 587;
	      final String username = "dasaotw@gmail.com";
	      final String password = "jdiin3737";//your password

	      // Get system properties
	      Properties properties = System.getProperties();
	      
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.port", port);
	      
	      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    	    protected PasswordAuthentication getPasswordAuthentication() {
	    	        return new PasswordAuthentication(username, password);
	    	    }
	    	});
	      
	      try {

	    	   Message message = new MimeMessage(session);
	    	   message.setFrom(new InternetAddress("fromn@gmail.com"));
	    	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    	   if(mailFlag == 0) {	//welcome mail
	    		   message.setSubject("歡迎使用Dasao.");
	    		   
	    		   message.setText("Dear " + userObj.getUser_id() + ", 歡迎使用Dasao");	    		   
	    	   }else if(mailFlag == 1) { //forget mail
	    		   message.setSubject(userObj.getUser_id() +",你好 您在Dasao的密碼是");
	    		   message.setText("Dear " + userObj.getUser_id() + ",你好 您在Dasao的密碼是" + userObj.getPassword());	 
	    	   }

	    	   Transport transport = session.getTransport("smtp");
	    	   transport.connect(host, port, username, password);

	    	   Transport.send(message);

	    	   System.out.println("mailFlag:" + mailFlag + " to " +userObj.getEmail() + ", 寄送email結束.");

	    	  } catch (MessagingException e) {
	    	   throw new RuntimeException(e);
	    	  }	    	 
	      
	   }

}
