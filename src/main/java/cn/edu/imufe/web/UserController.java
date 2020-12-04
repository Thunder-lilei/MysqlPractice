package cn.edu.imufe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.entity.Role;
import cn.edu.imufe.entity.User;
import cn.edu.imufe.entity.UserRole;
import cn.edu.imufe.service.RoleService;
import cn.edu.imufe.service.RoleWithUserRoleService;
import cn.edu.imufe.service.UserRoleService;
import cn.edu.imufe.service.UserService;
import cn.edu.imufe.util.UserUtil;

/**
 * @author lilei
 * @function
 * @param
 * @return 
 * 2020年11月25日下午5:07:35
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
	
	private final String MESSAGE = "message";
	private final String MESSAGE_SUCCESS = "success";
	private final String REQUEST_PAGE_INDEX = "redirect:/index.html";
	private final String REQUEST_PAGE_LOGIN = "redirect:/admin/login.html";
	private final String REQUEST_PAGE_STUDENT_INDEX = "redirect:/student/studentIndex.html";
	private final String REQUEST_PAGE_TEACHER_ADMIN_INDEX = "redirect:/teacher/teacherIndex.html";
	
	/**
	 * @功能	管理员登陆 保存登录信息至session 跳转至index.html
	 * @参数	用户名username 密码password
	 * @返回值 index.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	private ModelAndView login(@RequestParam String username,@RequestParam String password){
		ModelAndView mv = new ModelAndView(REQUEST_PAGE_INDEX);
		User auser = auserService.selectByUsername(username);
		if(auser!=null) 
		{
			if(auser.getPassword().equals(password)) 
			{
				session.setAttribute(roleWithUserRoleService.getRole(auser.getId()), auser);
				session.setAttribute("user", auser);
				mv.addObject(MESSAGE, MESSAGE_SUCCESS);
				UserRole userRole = userRoleService.selectByUserId(auser.getId());
				Role role = roleService.selectByPrimaryKey(userRole.getRoleId());
				System.out.println(role.getName());
				switch(role.getName()) {
					case "admin":
						mv = new ModelAndView(REQUEST_PAGE_TEACHER_ADMIN_INDEX);
						break;
					case "student":
						mv = new ModelAndView(REQUEST_PAGE_STUDENT_INDEX);
						break;
					case "teacher":
						mv = new ModelAndView(REQUEST_PAGE_TEACHER_ADMIN_INDEX);
						break;
					default:
						mv.addObject(MESSAGE, "错误的用户角色");
						break;
				}
				return mv;
			}else 
			{
				mv.addObject(MESSAGE, "错误的密码");
			}
		}else 
		{
			mv.addObject(MESSAGE, "错误的账号");
		}
		return mv;
	}
	/**
	 * @功能	修改密码 跳转至login.html
	 * @参数	用户名username 新密码newpassword
	 * @返回值 login.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	private ModelAndView updatePassword(@RequestParam String username,@RequestParam String newPassword){
		ModelAndView mv = new ModelAndView(REQUEST_PAGE_LOGIN);
		User auser = auserService.selectByUsername(username);
		if(auser!=null) 
		{
			auser.setPassword(newPassword);
			Integer result = auserService.updatePasswordByUsernameSelective(auser);
			if(result.equals(1)) 
			{
				mv.addObject(MESSAGE, MESSAGE_SUCCESS);
				return mv;
			}else 
			{
				mv.addObject(MESSAGE, "修改失败");
			}
		}else 
		{
			mv.addObject(MESSAGE, "错误的用户名");
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
		ModelAndView mv = new ModelAndView(REQUEST_PAGE_INDEX);
		//已登录
		if(UserUtil.IfLogin(session)) 
		{
			User record = (User)session.getAttribute("user");
			session.setAttribute(roleService.selectByPrimaryKey(userRoleService.selectByUserId(record.getId()).getRoleId()).getName(), null);
			//清空session
			session.invalidate();
			mv.addObject(MESSAGE, MESSAGE_SUCCESS);
			return mv;
		}else
		{
			mv.addObject(MESSAGE, "未登录");
			mv = new ModelAndView(REQUEST_PAGE_LOGIN);
		}
		return mv;
	}
	
}
