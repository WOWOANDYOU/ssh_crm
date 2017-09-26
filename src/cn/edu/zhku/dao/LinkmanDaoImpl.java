package cn.edu.zhku.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.edu.zhku.entity.LinkMan;

public class LinkmanDaoImpl implements LinkmanDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void add(LinkMan linkman) {
		hibernateTemplate.save(linkman);
	}

	@Override
	public List<LinkMan> findall() {
		List<LinkMan> list = (List<LinkMan>) hibernateTemplate.find("from LinkMan");
		if(list!=null && list.size()!=0) {
			return list;
		}
		return null;
	}

	@Override
	public LinkMan findOne(Integer linkid) {
		return this.hibernateTemplate.get(LinkMan.class,linkid);
	}

	@Override
	public void update(LinkMan linkman) {
		this.hibernateTemplate.update(linkman);
	}
	
}
