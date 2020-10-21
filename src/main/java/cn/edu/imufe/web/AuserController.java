package cn.edu.imufe.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.dao.AuserDao;
import cn.edu.imufe.entity.Answer;
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
	/**
	 * @功能	管理员登陆 保存登录信息至session 跳转至index.html
	 * @参数	用户名username 密码password
	 * @返回值 index.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	private ModelAndView login(@RequestParam String username,String password){
		ModelAndView mv = new ModelAndView("redirect:/index.html");
		Auser auser = auserDao.selectByUsername(username);
		if(auser!=null) 
		{
			if(auser.getPassword().equals(password)) 
			{
				session.setAttribute("user", auser);
				mv.addObject("message", "success");
				return mv;
			}else 
			{
				mv.addObject("message", "错误的密码！");
			}
		}else 
		{
			mv.addObject("message", "错误的账号！");
		}
		return mv;
	}
	
}
