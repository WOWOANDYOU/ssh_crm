package cn.edu.zhku.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zhku.dao.CustomerDao;
import cn.edu.zhku.entity.Customer;
import cn.edu.zhku.entity.PageBean;
@Transactional  //��service�� Ҫ�������� ��ע��  ��Ȼ�ᱨ  Transaction readonly commit/auto ����
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
		//���� ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		//����ÿҳ��ʾ�ļ�¼��
		int pageSize = 4;
		pageBean.setPageSize(pageSize);
		
		//���÷��� ��ȡ�ܵļ�¼��
		int totalcount = customerdao.findTotalCount();
		//���� pageBean���ܼ�¼��
		pageBean.setTotalCount(totalcount);
		
		//���� ��ʼҳ ��ʽ:(��ǰҳ-1)*ÿҳ��¼��
		int begin = (currentPage-1)*4;
		pageBean.setBegin(begin);
		
		//���� ��ҳ��
		if(totalcount%pageSize==0) {
			pageBean.setTotalPage(totalcount/pageSize);
		}else {
			pageBean.setTotalPage((totalcount/pageSize)+1);
		}
		
		//���÷����õ�list ��ÿҳ��list ���ϣ�
		List<Customer> list = customerdao.findlistPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	public List<Customer> findCondition(Customer customer) {
		return customerdao.findCondition(customer);
	}
	
}
