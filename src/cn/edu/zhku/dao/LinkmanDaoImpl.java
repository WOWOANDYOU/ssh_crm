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

	//�Ǿ��������߶����ѯ ���ݿ�
	@Override
	public List<LinkMan> findcomplex(LinkMan linkman) {
		//�ȴ������� ���� ��˵���Ƕ��ĸ��������
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		//��� ��ѯ����  ���ж� ��ѯ�����Ƿ�Ϊ�� �����Ϊ�� �����ý�ȥ �����裬
		//�������if������������ ��ô�� û������ ��������в�ѯ  ���û���ж����ֱ������ ��ô���û��д������
		//��ô���ý�ȥ�Ľ����� null ��Ȼ��ѯ���Ľ��Ҳ��null
		if(linkman.getLinkName()!=null && !"".equals(linkman.getLinkName())) {
			criteria.add(Restrictions.eq("linkName", linkman.getLinkName()));
		}
		if(linkman.getCustomer().getCid()!=0) {
			criteria.add(Restrictions.eq("customer.cid", linkman.getCustomer().getCid()));
		}
		
		//ִ�����߶���
		List<LinkMan> list = (List<LinkMan>) this.hibernateTemplate.findByCriteria(criteria);
		return list;
	}
}
