package cn.edu.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
	public void delete(LinkMan linkman) {
		this.hibernateTemplate.delete(linkman);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkMan> findbyName(String linkName) {
		return (List<LinkMan>) this.hibernateTemplate.find("from LinkMan where linkName=?", linkName);
	}

	//那就利用离线对象查询 数据咯
	@Override
	public List<LinkMan> findcomplex(LinkMan linkman) {
		//先创建离线 对象 并说明是对哪个对象操作
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//添加 查询条件  先判断 查询条件是否为空 如果不为空 就设置进去 否则不设，
		//如果两个if条件都不成立 那么就 没有条件 会进行所有查询  如果没有判断语句直接设置 那么如果没填写条件，
		//那么设置进去的将会是 null 自然查询到的结果也是null
		if(linkman.getLinkName()!=null && !"".equals(linkman.getLinkName())) {
			criteria.add(Restrictions.eq("linkName", linkman.getLinkName()));
		}
		if(linkman.getCustomer().getCid()!=0) {
			criteria.add(Restrictions.eq("customer.cid", linkman.getCustomer().getCid()));
		}
		
		//执行离线对象
		List<LinkMan> list = (List<LinkMan>) this.hibernateTemplate.findByCriteria(criteria);
		return list;
	}
}
