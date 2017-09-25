package cn.edu.zhku.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zhku.dao.CustomerDao;
import cn.edu.zhku.entity.Customer;
import cn.edu.zhku.entity.PageBean;
@Transactional  //在service中 要开启事务 的注解  不然会报  Transaction readonly commit/auto 错误
public class CustomerService {
	private CustomerDao customerdao;

	public void setCustomerdao(CustomerDao customerdao) {
		this.customerdao = customerdao;
	}
	public void addCust(Customer cust) {
		customerdao.add(cust);
	}
	public List<Customer> findAllCust(){
		return customerdao.findAllCust();
	}
	public Customer findOneCust(int cid) {
		return customerdao.findOneCust(cid);
	}
	public void deleteCust(Customer cust) {
		customerdao.deleteCust(cust);
	}
	public void updateCust(Customer cust) {
		customerdao.updateCust(cust);
	}
	public PageBean listPage(int currentPage) {
		PageBean pageBean = new PageBean();
		//设置 当前页
		pageBean.setCurrentPage(currentPage);
		//设置每页显示的记录数
		int pageSize = 4;
		pageBean.setPageSize(pageSize);
		
		//调用方法 获取总的记录数
		int totalcount = customerdao.findTotalCount();
		//设置 pageBean的总记录数
		pageBean.setTotalCount(totalcount);
		
		//设置 起始页 公式:(当前页-1)*每页记录数
		int begin = (currentPage-1)*4;
		pageBean.setBegin(begin);
		
		//计算 总页数
		if(totalcount%pageSize==0) {
			pageBean.setTotalPage(totalcount/pageSize);
		}else {
			pageBean.setTotalPage((totalcount/pageSize)+1);
		}
		
		//调用方法得到list （每页的list 集合）
		List<Customer> list = customerdao.findlistPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	public List<Customer> findCondition(Customer customer) {
		return customerdao.findCondition(customer);
	}
	
}
