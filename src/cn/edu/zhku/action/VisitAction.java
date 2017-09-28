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

	//要查询 客户列表 注入 customerservice
	private CustomerService customerservice;
	//要查询用户列表 
	private UserService userservice;
	
	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	//到添加 拜访页面
	public String addvisit() {
		//先查询 客户 联系人 列表
		List<Customer> custlist = customerservice.findAllCust();
		List<User> userlist = userservice.findall();
		
		ServletActionContext.getRequest().setAttribute("custlist", custlist);
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		
		return "toaddpage";
	}
	public String add() {
		visitservice.save(visit);
		return "showall";
	}
	
	public String showall() {
		List<Visit> visitlist = visitservice.findall();
		ServletActionContext.getRequest().setAttribute("visitlist", visitlist);
		return "showall";
	}
}
