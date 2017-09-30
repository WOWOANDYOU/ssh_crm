package cn.edu.zhku.dao;

import java.util.List;

import cn.edu.zhku.entity.Customer;
import cn.edu.zhku.entity.Dict;

public interface CustomerDao {
	public void add(Customer cust);
	public List<Customer> findAllCust();
	public Customer findOneCust(int cid);
	public void deleteCust(Customer cust);
	public void updateCust(Customer cust);
	public int findTotalCount();
	public List<Customer> findlistPage(int begin, int pageSize);
	public List<Customer> findCondition(Customer customer);
	public List<Customer> findcomplex(Customer customer);
	public List<Dict> findcustlevel();
}
