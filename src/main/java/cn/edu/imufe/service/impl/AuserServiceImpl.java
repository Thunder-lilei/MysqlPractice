package cn.edu.imufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.AuserDao;
import cn.edu.imufe.entity.Auser;
import cn.edu.imufe.service.AuserService;
@Service
public class AuserServiceImpl implements AuserService {
	@Autowired
	private AuserDao Auserdao;

	@Override
	public List<Auser> SelectAllUser() {
		// TODO Auto-generated method stub
		return Auserdao.SelectAllUser();
	}

}
