package cn.edu.zhku.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.zhku.entity.Customer;
import cn.edu.zhku.entity.LinkMan;
import cn.edu.zhku.service.CustomerService;
import cn.edu.zhku.service.LinkmanService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkmanService linkmanservice;
	private CustomerService customerservice;
	
	//文件上传 
	//两个变量 1：表示上传的文件
	//		2：表示 上传文件的文件名
	//		3:生成 get set 方法
	//       这两个变量 是有命名规范的
	//		File 的名字 是 表单file的 name值
	//		文件名 是 file 的name值 加上 FileName
	private File upload;
	private String uploadFileName;
	
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}

	public void setLinkmanservice(LinkmanService linkmanservice) {
		this.linkmanservice = linkmanservice;
	}

	//模型驱动
	private LinkMan linkman = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkman;
	}
	
	public String findAllLinkMan() {
		List<LinkMan> list = linkmanservice.findall();
		ServletActionContext.getRequest().setAttribute("listlink", list);
		return "showlinks";
	}
	public String addLinkMan() {
		//添加联系人 要选客户 所以先查客户 下拉列表显示(调用 CustomerAction 中的方法)
		List<Customer> list = customerservice.findAllCust();
		ServletActionContext.getRequest().setAttribute("custlist", list);
		return "toaddpage";
	}
	public String add() {
		//这样做是 不会将 外键（客户的id封装进 联系人中去的 ） 两种方式
		//第一种 传统方式 代码实现
		/*String strcustid = ServletActionContext.getRequest().getParameter("custid");
		int custid = Integer.parseInt(strcustid);
		Customer customer = new Customer();
		customer.setCid(custid);
		
		linkman.setCustomer(customer);*/
		//第二种 就是 在前端页面中 的下拉列表 select 标签的 name属性 改为 customer.cid 这样就可以 封装进 linkman实体对象中 去了
		
		//判断用户有没有 上传文件进来
		if(upload!=null) {
			File servicefile = new File("E:\\SQL"+"\\"+uploadFileName);
			//将文件复制到 服务器本地磁盘
			try {
				FileUtils.copyFile(upload, servicefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		linkmanservice.add(linkman);
		return "add";
	}
	public String toeditpage() {
		//转到 修改 页面前 需要显示原有数据
		//由于这里还需要 显示 客户下拉列表 所以 也要查出所有客户
		List<Customer> listcust = customerservice.findAllCust();
		
		LinkMan linkman2 = linkmanservice.findOne(linkman.getLinkid());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listcust", listcust);
		request.setAttribute("linkman", linkman2);
		return "toeditpage";
	}
	
	//修改联系人 信息
	public String update() {
		linkmanservice.update(linkman);
		return "update";
	}
	//删除联系人
	public String delete() {
		linkmanservice.delete(linkman);
		return "deletelinkman";
	}
	//根据联系人名字查询
	public String findOne() {
		List<LinkMan> listlink = linkmanservice.findOne(linkman.getLinkName());
		ServletActionContext.getRequest().setAttribute("listlink", listlink);
		return "findpage";
	}
}
