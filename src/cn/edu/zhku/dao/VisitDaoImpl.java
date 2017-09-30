package cn.edu.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public List<Visit> findcomplex(Visit visit) {
		//利用离线查询 数据
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		if(visit.getUser().getUid()!=0) {
			criteria.add(Restrictions.eq("user.uid", visit.getUser().getUid()));
		}
		if(visit.getCustomer().getCid()!=0) {
			criteria.add(Restrictions.eq("customer.cid", visit.getCustomer().getCid()));
		}
		
		@SuppressWarnings("unchecked")
		List<Visit> list = (List<Visit>) this.hibernateTemplate.findByCriteria(criteria);
		return list;
	}
	
}
