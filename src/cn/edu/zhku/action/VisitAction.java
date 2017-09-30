package cn.edu.zhku.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.zhku.entity.Customer;
import cn.edu.zhku.entity.User;
import cn.edu.zhku.entity.Visit;
import cn.edu.zhku.service.CustomerService;
import cn.edu.zhku.service.UserService;
import cn.edu.zhku.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		return visit;
	}
	
	private VisitService visitservice;
	
	public void setVisitservice(VisitService visitservice) {
		this.visitservice = visitservice;
	}

	//Ҫ��ѯ �ͻ��б� ע�� customerservice
	private CustomerService customerservice;
	//Ҫ��ѯ�û��б� 
	private UserService userservice;
	
	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	//����� �ݷ�ҳ��
	public String addvisit() {
		//�Ȳ�ѯ �ͻ� ��ϵ�� �б�
		List<Customer> custlist = customerservice.findAllCust();
		List<User> userlist = userservice.findall();
		
		ServletActionContext.getRequest().setAttribute("custlist", custlist);
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		
		return "toaddpage";
	}
	public String add() {
		visitservice.save(visit);
		return "toshowall";
	}
	
	public String showall() {
		List<Visit> visitlist = visitservice.findall();
		ServletActionContext.getRequest().setAttribute("visitlist", visitlist);
		return "showall";
	}
	
	// ��������ѯǰ
	 public String beforefindcomplex() {
		 List<Customer> listcust = customerservice.findAllCust();
		 List<User> listuser = userservice.findall();
		 ServletActionContext.getRequest().setAttribute("listcust", listcust);
		 ServletActionContext.getRequest().setAttribute("listuser", listuser);
		 return "goselectpage";
		 
	 }
	//��������ѯ
	public String findcomplex() {
		List<Visit> list = visitservice.findcomplex(visit);
		ServletActionContext.getRequest().setAttribute("visitlist", list);
		return "toselectpage";
	}
}
