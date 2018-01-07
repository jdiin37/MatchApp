package dto;

import java.sql.Blob;
import java.time.LocalDateTime;



public class img_basicObj {

	private Integer id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	private Integer post_id;
	
	private Blob img;

	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public Blob getImg() {
		return img;
	}
	public void setImg(Blob blob) {
		this.img = blob;
	}
}
