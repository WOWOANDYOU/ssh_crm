package cn.edu.zhku.entity;
//��ʵ�� ��¼�� �ͻ� �û�֮��İݷü�¼

public class Visit {
	private Integer vid;
	private String vaddress;
	private String vcontent;
	//һ�� �û������ж�� �ݷü�¼
	private User user;
	//һ���ͻ�ͬ�������ж�� �ݷü�¼
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
