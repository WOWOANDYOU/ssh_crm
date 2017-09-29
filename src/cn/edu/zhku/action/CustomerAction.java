package cn.edu.zhku.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.zhku.entity.Customer;
import cn.edu.zhku.entity.PageBean;
import cn.edu.zhku.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private CustomerService customerservice;

	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}
	
	//����ģ������ �� �ύ���������� ��װ�� ������
	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//���Է�װ ��ҳ��ѯ 
	private int currentPage;
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	//1 �����ҳ��
	public String toadd() {
		return "toaddpage";
	}
	
	//2 ��ӷ���
	public String add() {
		customerservice.addCust(customer);
		return "add";
	}
	//�� list ���뵽 ֵջ�� ��������:
	//1:����list����
	//2������ ������get��������ǰ�� ��struts��ǩ ��Iterator ��ǩ ���ã�
	//3����action�ķ����� �� list������ֵ
	
	//����list ���� 
	private List<Customer> list;
	//���� get����
	public List<Customer> getList() {
		return list;
	}
	
	
	//3��� �ͻ��� չʾ �ͻ���Ϣ
	public String findAllCust(){
		list = customerservice.findAllCust();//�� list������ӵ� ֵջ��
		//���� ����session�� ǰ���� el���ʽ ȡ�� �� �����Խ������ֵջ��  ǰ���� struts2��ǩ ȡ������
		/*HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("list", list);*/
		
		return "showCusts";
	}
	
	public String delete() {
		//���ڸ� action������ ģ��������װ post �� get ���� �������Ĳ���  ��ô ���û� ��� ɾ�� ��ť��ʱ�� �ͻ� ���� ��Ҫɾ���� �ͻ���id
		int cid = customer.getCid();
		
		//ɾ�� �淶 �Ȳ��� ��ɾ��
		Customer cust = customerservice.findOneCust(cid);
		if(cust!=null) {
			//ɾ�� �ö���
			customerservice.deleteCust(cust);
		}
		
		return "delete";
	}
	//���޸�ҳ��
	public String beforeEdit() {
		//�Ȳ������ �浽 request�����
		int cid = customer.getCid();
		Customer cust = customerservice.findOneCust(cid);
		ServletActionContext.getRequest().setAttribute("customer", cust);
		return "toeditpage";
	}
	//�޸��û���Ϣ
	public String edit() {
		customerservice.updateCust(customer);
		return "updateCust";
	}
	
	//��ҳ��ѯ
	public String listpage() {
		PageBean pageBean = customerservice.listPage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//������ѯ  ���ݿͻ�����ѯ
	public String findCondition() {
		if(customer.getCustName()!=null && !("".equals(customer.getCustName()))) {
			List<Customer> list2 = customerservice.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list2", list2);
		}else
			list = customerservice.findAllCust();//�� list������ӵ� ֵջ��
		return "conditionpage";
	}
	
	//��������ѯ
	public String findcomplex() {
		List<Customer> custlist = customerservice.findcomplex(customer);
		ServletActionContext.getRequest().setAttribute("custlist", custlist);
		return "selectpage";
	}
}
