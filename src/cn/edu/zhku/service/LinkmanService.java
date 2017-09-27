package cn.edu.zhku.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zhku.dao.LinkmanDao;
import cn.edu.zhku.entity.LinkMan;
@Transactional
public class LinkmanService {
	private LinkmanDao linkmandao;

	public void setLinkmandao(LinkmanDao linkmandao) {
		this.linkmandao = linkmandao;
	}

	public void add(LinkMan linkman) {
		linkmandao.add(linkman);
	}

	public List<LinkMan> findall() {
		return linkmandao.findall();
	}

	public LinkMan findOne(Integer linkid) {
		return linkmandao.findOne(linkid);
	}

	public void update(LinkMan linkman) {
		linkmandao.update(linkman);		
	}

	public void delete(LinkMan linkman) {
		linkmandao.delete(linkman);
		
	}

	public List<LinkMan> findOne(String linkName) {
		return linkmandao.findbyName(linkName);
		
	}
	
}
