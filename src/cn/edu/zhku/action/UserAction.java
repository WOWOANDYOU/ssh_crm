package cn.edu.zhku.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.zhku.entity.User;
import cn.edu.zhku.service.UserService;

public class UserAction extends ActionSupport {
	private UserService userservice;
	
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		User userin = userservice.login(user);
		if(userin!=null) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", userin);
			return "loginsuccess";
		}else
			return "login";
	}
}
