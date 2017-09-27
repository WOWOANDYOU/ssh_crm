package cn.edu.zhku.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer uid;
	private String username;
	private String password;
	private String address;
	//一个用户 可以有多个 拜访客户的记录
	Set<Visit> setvisit = new HashSet<Visit>();
	
	public Set<Visit> getSetvisit() {
		return setvisit;
	}
	public void setSetvisit(Set<Visit> setvisit) {
		this.setvisit = setvisit;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
