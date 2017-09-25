package cn.edu.zhku.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zhku.dao.UserDao;
import cn.edu.zhku.entity.User;
@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User login(User user) {
		return userDao.loginUser(user);
	}
}
