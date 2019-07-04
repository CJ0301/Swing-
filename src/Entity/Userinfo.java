package Entity;

import java.util.Date;

public class Userinfo {
	private Integer uid;
	private String phone;
	private String password;
	private String tname;
	private String id;
	private Character genda;
	private Date regtime;
	private Float amount;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Character getGenda() {
		return genda;
	}

	public void setGenda(Character genda) {
		this.genda = genda;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Userinfo(String phone, String password, String tname, String id, Character genda) {
		super();
		this.phone = phone;
		this.password = password;
		this.tname = tname;
		this.id = id;
		this.genda = genda;
	}

	public Userinfo() {
		// TODO Auto-generated constructor stub
	}

	public Userinfo(String tname, String password) {
		this.tname = tname;
		this.password = password;
	}

}
