package cn.edu.imufe.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.entity.Auser;
import cn.edu.imufe.service.AuserService;
import cn.edu.imufe.service.impl.AuserServiceImpl;
import cn.edu.imufe.util.IfLoginUtil;
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
		System.out.println(username+"请求登录");
		Auser auser = auserService.selectByUsername(username);
		if(auser!=null) 
		{
			if(auser.getPassword().equals(password)) 
			{
				switch(auser.getRole()) {
					case "student":
						System.out.println("是学生");
						session.setAttribute("student", auser);
						break;
					case "teacher":
						System.out.println("是老师");
						session.setAttribute("teacher", auser);
						break;
					case "admin":
						System.out.println("是管理员");
						session.setAttribute("admin", auser);
						break;
				}
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
		ModelAndView mv = new ModelAndView("redirect:/admin/login.html");
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
	/**
	 * @功能	登出 跳转至index.html
	 * @参数	无参
	 * @返回值 index.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	private ModelAndView logout(){
		ModelAndView mv = new ModelAndView("redirect:/index.html");
		//已登录
		if(IfLoginUtil.IfLogin(session)) 
		{
			Auser record = (Auser)session.getAttribute("user");
			System.out.println(record.getRole()+record.getNickname()+"登出");
			session.setAttribute(record.getRole(), null);
			session.setAttribute("user", null);
			mv.addObject("message", "success");
			return mv;
		}else//未登录
		{
			mv.addObject("message", "未登录");
			mv = new ModelAndView("redirect:/admin/login.html");
		}
		return mv;
	}
	
}
