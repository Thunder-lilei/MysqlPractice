package cn.edu.imufe.service;

import java.util.List;

import cn.edu.imufe.entity.Auser;

public interface AuserService {
	List<Auser> SelectAllUser();
	
	Auser selectByUsername(String username);
}
