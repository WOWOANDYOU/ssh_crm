package cn.edu.zhku.dao;

import java.util.List;

import cn.edu.zhku.entity.LinkMan;

public interface LinkmanDao {
	public void add(LinkMan linkman);

	public List<LinkMan> findall();

	public LinkMan findOne(Integer linkid);

	public void update(LinkMan linkman);

	public void delete(LinkMan linkman);

	public List<LinkMan> findbyName(String linkName);
}	
