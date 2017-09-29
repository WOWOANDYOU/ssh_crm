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
	
	//利用模型驱动 将 提交过来的数据 封装到 对象中
	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//属性封装 分页查询 
	private int currentPage;
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	//1 到添加页面
	public String toadd() {
		return "toaddpage";
	}
	
	//2 添加方法
	public String add() {
		customerservice.addCust(customer);
		return "add";
	}
	//将 list 存入到 值栈中 两部操作:
	//1:定义list变量
	//2：生成 变量的get方法（供前端 的struts标签 的Iterator 标签 调用）
	//3：在action的方法中 对 list变量赋值
	
	//定义list 变量 
	private List<Customer> list;
	//生成 get方法
	public List<Customer> getList() {
		return list;
	}
	
	
	//3添加 客户后 展示 客户信息
	public String findAllCust(){
		list = customerservice.findAllCust();//将 list变量添加到 值栈中
		//除了 存在session中 前端用 el表达式 取得 外 还可以将其存在值栈中  前端用 struts2标签 取出数据
		/*HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("list", list);*/
		
		return "showCusts";
	}
	
	public String delete() {
		//由于该 action利用了 模型驱动封装 post 和 get 请求 带过来的参数  那么 当用户 点击 删除 按钮的时候 就会 带来 想要删除的 客户的id
		int cid = customer.getCid();
		
		//删除 规范 先查找 再删除
		Customer cust = customerservice.findOneCust(cid);
		if(cust!=null) {
			//删除 该对象
			customerservice.deleteCust(cust);
		}
		
		return "delete";
	}
	//到修改页面
	public String beforeEdit() {
		//先查出数据 存到 request域对象
		int cid = customer.getCid();
		Customer cust = customerservice.findOneCust(cid);
		ServletActionContext.getRequest().setAttribute("customer", cust);
		return "toeditpage";
	}
	//修改用户信息
	public String edit() {
		customerservice.updateCust(customer);
		return "updateCust";
	}
	
	//分页查询
	public String listpage() {
		PageBean pageBean = customerservice.listPage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	//条件查询  根据客户名查询
	public String findCondition() {
		if(customer.getCustName()!=null && !("".equals(customer.getCustName()))) {
			List<Customer> list2 = customerservice.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list2", list2);
		}else
			list = customerservice.findAllCust();//将 list变量添加到 值栈中
		return "conditionpage";
	}
	
	//多条件查询
	public String findcomplex() {
		List<Customer> custlist = customerservice.findcomplex(customer);
		ServletActionContext.getRequest().setAttribute("custlist", custlist);
		return "selectpage";
	}
}
