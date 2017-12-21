package dto;

import java.time.LocalDateTime;

public class post_basicObj {

	private Integer id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String user_id;
	private String location;
	private String location_desc;
	private String demand_desc;
	private Integer fee;
	private String status;
	private String cre_date;
	private String mod_date;
	
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation_desc() {
		return location_desc;
	}
	public void setLocation_desc(String location_desc) {
		this.location_desc = location_desc;
	}
	public String getDemand_desc() {
		return demand_desc;
	}
	public void setDemand_desc(String demand_desc) {
		this.demand_desc = demand_desc;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public String getMod_date() {
		if(mod_date == null || mod_date.equals("")) {
			return LocalDateTime.now().toString();
		}else {
			return mod_date;			
		}
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	
}
