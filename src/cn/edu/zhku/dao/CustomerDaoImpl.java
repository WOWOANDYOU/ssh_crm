package cn.edu.zhku.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.edu.zhku.entity.Customer;

public class CustomerDaoImpl implements CustomerDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void add(Customer cust) {
		this.hibernateTemplate.save(cust);
	}

	@Override
	public List<Customer> findAllCust() {
		ArrayList<Customer> list = (ArrayList<Customer>) this.hibernateTemplate.find("from Customer");
		return list;
	}

	@Override
	public Customer findOneCust(int cid) {
		return this.hibernateTemplate.get(Customer.class, cid);
	}

	@Override
	public void deleteCust(Customer cust) {
		//ɾ���û�
		this.hibernateTemplate.delete(cust);
		
	}
	
	public void updateCust(Customer cust) {
		hibernateTemplate.update(cust);
	}

	//��ѯ ���ݿ��� ���Ӧ���ܼ�¼��
	@Override
	public int findTotalCount() {
		List<Object> list = (List<Object>) this.hibernateTemplate.find("select count(*) from Customer");
		int totalCount = 0;
		if(list!=null && list.size()!=0) {
			Object o = list.get(0);
			totalCount = Integer.parseInt(o.toString());
		}
		return totalCount;
	}
	//��� ��beginΪ��ʼ����pageSizeΪÿҳ��¼������ list 
	@Override
	public List<Customer> findlistPage(int begin, int pageSize) {
		// �����ַ��� ��һ���˽� �ڶ����ǶԵ�һ�ֵķ�װ ���� �ڶ��ֿ���
		/*SessionFactory sessionFactory = this.hibernateTemplate.getSessionFactory();
		//��ȡsession����
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Customer");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		
		List<Customer> list = query.list();*/
		
		//�ڶ��ַ����ǶԵ�һ�ַ����ķ�װ �õڶ��־Ϳ�����
		//�ڶ��ַ����� ���� ���߶��� ��hibernateTemplate����
		//1:�ȴ������߶���  ���ö��ĸ�ʵ������в���
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//��һ������ ���߶���
		//�ڶ������� ��ʼλ��
		// ���������� �������
		List<Customer> list = (List<Customer>) this.hibernateTemplate.findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	public List<Customer> findCondition(Customer customer) {
		//���� ��������������ѯ  1���ǵ���hibernateTemplate ��find������
		//2 ������ ���߶��� �� hibernateTemplate ������ѯ
		//1������hibernateTemplate ��find������
		List<Customer> list = null;
		/*if(customer.getCustName()!=null && !("".equals(customer.getCustName()))) {
			list = (List<Customer>) this.hibernateTemplate.find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		}*/
		
		//�ڶ��ַ�ʽ ʹ�����߶���  �ڿ����� һ��ʹ�����ַ�ʽ
		//1 ���� ���߶��� ���Ǹ�ʵ�������
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2 ���ö�ʵ�����ĸ�����
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
	    list = (List<Customer>) this.hibernateTemplate.findByCriteria(criteria);
		return list;
	}

	//�������� �������²�ѯ
	@Override
	public List<Customer> findcomplex(Customer customer) {
		//��Ϊ ����֮���ж���� ����sql�����Ҫ ƴ��
		String sql = "from Customer where 1=1";
		
		//�� �����ŵ� ArrayList��
		ArrayList<String> array = new ArrayList<String>();
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			sql = sql+" and custName=?";
			array.add(customer.getCustName());
		}
		if(customer.getCustLevel()!=null && !"".equals(customer.getCustLevel())) {
			sql = sql+" and custLevel=?";
			array.add(customer.getCustLevel());
		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
			sql = sql+" and custSource=?";
			array.add(customer.getCustSource());
		}
		
		//�� ��ѯ��������
		@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) this.hibernateTemplate.find(sql, array.toArray());
		return list;
	}
	
}
