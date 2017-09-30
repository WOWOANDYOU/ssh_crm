package cn.edu.zhku.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zhku.dao.VisitDao;
import cn.edu.zhku.entity.Visit;
@Transactional
public class VisitService {
	private VisitDao visitdao;

	public void setVisitdao(VisitDao visitdao) {
		this.visitdao = visitdao;
	}

	public void save(Visit visit) {
		this.visitdao.save(visit);
	}

	public List<Visit> findall() {
		return visitdao.findall();
	}

	public List<Visit> findcomplex(Visit visit) {
		return visitdao.findcomplex(visit);
	}
	
}
