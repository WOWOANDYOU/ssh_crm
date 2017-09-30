package cn.edu.zhku.entity;
//数据字典的编写  客户中的等级 是不能随便写的 要提供已有的等级给选择 这个已有的等级就是数据字典 要在数据字典里查找
public class Dict {
	private String did;
	private String dname;
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
}
