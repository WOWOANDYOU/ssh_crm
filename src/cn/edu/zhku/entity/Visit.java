package cn.edu.zhku.entity;
//该实体 记录着 客户 用户之间的拜访记录

public class Visit {
	private Integer vid;
	private String vaddress;
	private String vcontent;
	//一个 用户可以有多个 拜访记录
	private User user;
	//一个客户同样可以有多个 拜访记录
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVcontent() {
		return vcontent;
	}
	public void setVcontent(String vcontent) {
		this.vcontent = vcontent;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	
	
}
