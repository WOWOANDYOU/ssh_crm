package cn.edu.zhku.entity;

public class LinkMan {
	private Integer linkid;
	private String linkName;
	private String linkGender;
	private String linkPhone;
	private String linkMobile;
	
	//���� �� �ͻ��Ĺ�ϵ һ���ͻ���Ӧ�����ϵ��  ����ϵ�������ͻ�
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getLinkid() {
		return linkid;
	}
	public void setLinkid(Integer linkid) {
		this.linkid = linkid;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkGender() {
		return linkGender;
	}
	public void setLinkGender(String linkGender) {
		this.linkGender = linkGender;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getLinkMobile() {
		return linkMobile;
	}
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	
}
