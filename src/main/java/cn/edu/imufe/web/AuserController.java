package cn.edu.imufe.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.imufe.dao.AuserDao;
import cn.edu.imufe.entity.Auser;
import cn.edu.imufe.service.impl.AuserServiceImpl;
@Controller
@RequestMapping(value="/user")
public class AuserController extends BaseController {
	@Autowired
	private AuserDao auserDao;
	@Autowired
	private AuserServiceImpl auserServiceImpl;
	@ResponseBody
	@RequestMapping(value="/AllUser",method=RequestMethod.GET)
	public List<Auser> SelectAllUser() {
		List<Auser> Auserlist=auserServiceImpl.SelectAllUser();
		return Auserlist;
	}
	
}
