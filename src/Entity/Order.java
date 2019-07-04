package Entity;

import java.util.Date;

public class Order {
	private String oid;
	private Date starttime;
	private Date endtime;
	private String image;
	private String user;
	private float amount;
	private String cid;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Order(String oid, String image) {
		super();
		this.oid = oid;
		this.image = image;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public float getAmount() {
		return amount;
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Order(Date starttime, Date endtime, String image, String user, float amount) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
		this.image = image;
		this.user = user;
		this.amount = amount;
	}
	/**
	 * Title: Order.java  
	 * @author shenlan  
	 * @date 2019年7月2日  
	 */
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
}
