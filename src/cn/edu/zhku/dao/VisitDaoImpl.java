package cn.edu.zhku.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.edu.zhku.entity.Visit;

public class VisitDaoImpl implements VisitDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void save(Visit visit) {
		this.hibernateTemplate.save(visit);
	}

	@Override
	public List<Visit> findall() {
		return (List<Visit>) this.hibernateTemplate.find("from Visit");
	}
	
}
