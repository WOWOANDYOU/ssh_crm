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
	
	//�ļ��ϴ� 
	//�������� 1����ʾ�ϴ����ļ�
	//		2����ʾ �ϴ��ļ����ļ���
	//		3:���� get set ����
	//       ���������� ���������淶��
	//		File ������ �� ��file�� nameֵ
	//		�ļ��� �� file ��nameֵ ���� FileName
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

	//ģ������
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
		//�����ϵ�� Ҫѡ�ͻ� �����Ȳ�ͻ� �����б���ʾ(���� CustomerAction �еķ���)
		List<Customer> list = customerservice.findAllCust();
		ServletActionContext.getRequest().setAttribute("custlist", list);
		return "toaddpage";
	}
	public String add() {
		//�������� ���Ὣ ������ͻ���id��װ�� ��ϵ����ȥ�� �� ���ַ�ʽ
		//��һ�� ��ͳ��ʽ ����ʵ��
		/*String strcustid = ServletActionContext.getRequest().getParameter("custid");
		int custid = Integer.parseInt(strcustid);
		Customer customer = new Customer();
		customer.setCid(custid);
		
		linkman.setCustomer(customer);*/
		//�ڶ��� ���� ��ǰ��ҳ���� �������б� select ��ǩ�� name���� ��Ϊ customer.cid �����Ϳ��� ��װ�� linkmanʵ������� ȥ��
		
		//�ж��û���û�� �ϴ��ļ�����
		if(upload!=null) {
			File servicefile = new File("E:\\SQL"+"\\"+uploadFileName);
			//���ļ����Ƶ� ���������ش���
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
		//ת�� �޸� ҳ��ǰ ��Ҫ��ʾԭ������
		//�������ﻹ��Ҫ ��ʾ �ͻ������б� ���� ҲҪ������пͻ�
		List<Customer> listcust = customerservice.findAllCust();
		
		LinkMan linkman2 = linkmanservice.findOne(linkman.getLinkid());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listcust", listcust);
		request.setAttribute("linkman", linkman2);
		return "toeditpage";
	}
	
	//�޸���ϵ�� ��Ϣ
	public String update() {
		linkmanservice.update(linkman);
		return "update";
	}
	//ɾ����ϵ��
	public String delete() {
		linkmanservice.delete(linkman);
		return "deletelinkman";
	}
	//������ϵ�����ֲ�ѯ
	public String findOne() {
		List<LinkMan> listlink = linkmanservice.findOne(linkman.getLinkName());
		ServletActionContext.getRequest().setAttribute("listlink", listlink);
		return "findpage";
	}
}
