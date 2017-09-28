package cn.edu.zhku.dao;

import java.util.List;

import cn.edu.zhku.entity.Visit;

public interface VisitDao {

	void save(Visit visit);

	List<Visit> findall();

}
