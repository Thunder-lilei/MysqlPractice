package cn.edu.imufe.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.entity.User;
import cn.edu.imufe.service.RoleService;
import cn.edu.imufe.service.RoleWithUserRoleService;
import cn.edu.imufe.service.UserRoleService;
import cn.edu.imufe.service.UserService;
import cn.edu.imufe.util.UserUtil;

/**
 * @author lilei
 *
 * 2020年11月20日
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	@Autowired
	private UserService auserService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleWithUserRoleService roleWithUserRoleService;
	@Autowired
	private RoleService roleService;
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
		User auser = auserService.selectByUsername(username);
		if(auser!=null) 
		{
			if(auser.getPassword().equals(password)) 
			{
				session.setAttribute(roleWithUserRoleService.getRole(auser.getId()), auser);
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
		User auser = auserService.selectByUsername(username);
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
		if(UserUtil.IfLogin(session)) 
		{
			User record = (User)session.getAttribute("user");
			session.setAttribute(roleService.selectByPrimaryKey(userRoleService.selectByUserId(record.getId()).getRoleId()).getName(), null);
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
