package cn.edu.zhku.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Integer cid;
	private String custName;
	/*private String custLevel;*/
	private String custSource;
	private String custPhone;
	private String custMobile;
	
	//配置 客户 和联系人的关系  一个客户 对应多个联系人
	private Set<LinkMan> setLinkman = new HashSet<LinkMan>();
	
	//一个 客户也可以有多个 被拜访的记录
	private Set<Visit> setvisit = new HashSet<Visit>();
	
	//一个客户有一个 等级 （字典表）一对多
	private Dict dict;
	
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	public Set<Visit> getSetvisit() {
		return setvisit;
	}
	public void setSetvisit(Set<Visit> setvisit) {
		this.setvisit = setvisit;
	}
	public Set<LinkMan> getSetLinkman() {
		return setLinkman;
	}
	public void setSetLinkman(Set<LinkMan> setLinkman) {
		this.setLinkman = setLinkman;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/*public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}*/
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	
}
