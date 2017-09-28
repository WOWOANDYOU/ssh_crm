package cn.edu.zhku.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.edu.zhku.entity.User;

public class UserDaoImpl implements UserDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public User loginUser(User user) {
		List<User> list = (List<User>) hibernateTemplate.find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		
		//ֻ�� list ��Ϊ null  �� ��ߴ���ֵ
		if(list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> findall() {
		return (List<User>) this.hibernateTemplate.find("from User");
	}
	
}
