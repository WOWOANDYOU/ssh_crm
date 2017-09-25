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
		//删除用户
		this.hibernateTemplate.delete(cust);
		
	}
	
	public void updateCust(Customer cust) {
		hibernateTemplate.update(cust);
	}

	//查询 数据库中 表对应的总记录数
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
	//查出 以begin为起始数，pageSize为每页记录条数的 list 
	@Override
	public List<Customer> findlistPage(int begin, int pageSize) {
		// 有两种方法 第一种了解 第二种是对第一种的封装 会用 第二种开发
		/*SessionFactory sessionFactory = this.hibernateTemplate.getSessionFactory();
		//获取session对象
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Customer");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		
		List<Customer> list = query.list();*/
		
		//第二种方法是对第一种方法的封装 用第二种就可以了
		//第二种方法是 用了 离线对象 和hibernateTemplate方法
		//1:先创建离线对象  设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//第一个参数 离线对象
		//第二个参数 起始位置
		// 第三个参数 最大数量
		List<Customer> list = (List<Customer>) this.hibernateTemplate.findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	public List<Customer> findCondition(Customer customer) {
		//两种 方法进行条件查询  1：是调用hibernateTemplate 的find方法查
		//2 是利用 离线对象 和 hibernateTemplate 方法查询
		//1：调用hibernateTemplate 的find方法查
		List<Customer> list = null;
		/*if(customer.getCustName()!=null && !("".equals(customer.getCustName()))) {
			list = (List<Customer>) this.hibernateTemplate.find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		}*/
		
		//第二种方式 使用离线对象  在开发中 一般使用这种方式
		//1 创建 离线对象 对那个实体类操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2 设置对实体类哪个属性
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
	    list = (List<Customer>) this.hibernateTemplate.findByCriteria(criteria);
		return list;
	}
	
}
