package dto;

import java.time.LocalDateTime;

public class user_basicObj {
	private String serverMsg;
	private Integer id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServerMsg() {
		return serverMsg;
	}
	public void setServerMsg(String serverMsg) {
		this.serverMsg = serverMsg;
	}
	private String status;
	private String user_id;
	private String password;
	private String auth;
	private String cre_date;
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCre_date() {
		if(cre_date == null || cre_date.equals("")) {
			return LocalDateTime.now().toString();
		}else {
			return cre_date;			
		}
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
