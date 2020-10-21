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
import cn.edu.imufe.service.AuserService;
import cn.edu.imufe.service.impl.AuserServiceImpl;
@Controller
@RequestMapping(value="/user")
public class AuserController extends BaseController {
	@Autowired
	private AuserServiceImpl auserServiceImpl;
	@Autowired
	private AuserService auserService;
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
	private ModelAndView login(@RequestParam String username,@RequestParam String password){
		ModelAndView mv = new ModelAndView("redirect:/index.html");
		Auser auser = auserService.selectByUsername(username);
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
	/**
	 * @功能	修改密码 跳转至login.html
	 * @参数	用户名username 新密码newpassword
	 * @返回值 login.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/updatepassword",method=RequestMethod.POST)
	private ModelAndView updatepassword(@RequestParam String username,@RequestParam String newpassword){
		ModelAndView mv = new ModelAndView("redirect:/login.html");
		Auser auser = auserService.selectByUsername(username);
		if(auser!=null) 
		{
			auser.setPassword(newpassword);
			Integer result = auserService.updatePasswordByUsernameSelective(auser);
			if(result.equals(1)) 
			{
				mv.addObject("message", "success");
				return mv;
			}else 
			{
				mv.addObject("message", "修改失败！");
			}
		}else 
		{
			mv.addObject("message", "错误的用户名！");
		}
		return mv;
	}
	
}
