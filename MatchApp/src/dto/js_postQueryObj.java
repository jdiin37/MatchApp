package dto;

public class js_postQueryObj {

	private String method;
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	private Integer rowNumber;
	private Integer minId;
	private Integer maxId;	
	private Integer fee;	
	private String user_id;
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

	private String location;
	private String location_desc;
	private String demand_desc;

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getMinId() {
		return minId;
	}

	public void setMinId(Integer minId) {
		this.minId = minId;
	}

	public Integer getMaxId() {
		return maxId;
	}

	public void setMaxId(Integer maxId) {
		this.maxId = maxId;
	}

	
	
}
