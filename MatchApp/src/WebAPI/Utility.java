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
	
	public static void main(String [] args) {    
	      // Recipient's email ID needs to be mentioned.
	      String to = "jdiin@hotmail.com";

	      // Sender's email ID needs to be mentioned
	      //String from = "web@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "smtp.gmail.com";
	      int port = 587;
	      final String username = "Dasao@gmail.com";
	      final String password = "";//your password

	      // Get system properties
	      Properties properties = System.getProperties();

	      
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.port", port);
//	      Session session = Session.getInstance(properties, new Authenticator() {
//	       protected PasswordAuthentication getPasswordAuthentication() {
//	        return new PasswordAuthentication(username, password);
//	       }
//	      });
	      
	      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    	    protected PasswordAuthentication getPasswordAuthentication() {
	    	        return new PasswordAuthentication(username, password);
	    	    }
	    	});
	      
	      try {

	    	   Message message = new MimeMessage(session);
	    	   message.setFrom(new InternetAddress("fromn@gmail.com"));
	    	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    	   message.setSubject("測試寄信.");
	    	   message.setText("Dear Levin, \n\n 測試 測試 測試 測試 測試 測試 email !");

	    	   Transport transport = session.getTransport("smtp");
	    	   transport.connect(host, port, username, password);

	    	   Transport.send(message);

	    	   System.out.println("寄送email結束.");

	    	  } catch (MessagingException e) {
	    	   throw new RuntimeException(e);
	    	  }
	    	 
	      // Setup mail server
	      //properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      //Session session = Session.getDefaultInstance(properties);

//	      try {
//	         // Create a default MimeMessage object.
//	         MimeMessage message = new MimeMessage(session);
//
//	         // Set From: header field of the header.
//	         message.setFrom(new InternetAddress(from));
//
//	         // Set To: header field of the header.
//	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//	         // Set Subject: header field
//	         message.setSubject("This is the Subject Line!");
//
//	         // Now set the actual message
//	         message.setText("This is actual message");
//
//	         // Send message
//	         Transport.send(message);
//	         System.out.println("Sent message successfully....");
//	      } catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
	   }

}
