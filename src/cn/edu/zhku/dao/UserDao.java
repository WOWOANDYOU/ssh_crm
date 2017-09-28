package cn.edu.zhku.dao;

import java.util.List;

import cn.edu.zhku.entity.User;

public interface UserDao {
	public User loginUser(User user);

	public List<User> findall();
}
