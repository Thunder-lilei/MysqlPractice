package cn.edu.imufe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.constant.PageUrlConstant;
import cn.edu.imufe.constant.UserConstant;
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
 * 2020年11月25日下午5:07:35
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	private final UserService userService;
	private final UserRoleService userRoleService;
	private final RoleWithUserRoleService roleWithUserRoleService;
	private final RoleService roleService;
	@Autowired
	public UserController(UserService userService, UserRoleService userRoleService,
						  RoleWithUserRoleService roleWithUserRoleService, RoleService roleService) {
	this.userService = userService;
	this.userRoleService = userRoleService;
	this.roleWithUserRoleService = roleWithUserRoleService;
	this.roleService = roleService;
	}

	private final String MESSAGE = "message";
	private final String MESSAGE_SUCCESS = "success"	;
	
	/**
	 * @功能	管理员登陆 保存登录信息至session 跳转至index.html
	 * @参数	用户名username 密码password
	 * @返回值 index.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/toLogin",method=RequestMethod.POST)
	private ModelAndView toLogin(@RequestParam String username,@RequestParam String password){
		ModelAndView mv = new ModelAndView(PageUrlConstant.INDEX);
		User user = userService.selectByUsername(username);
		if(user!=null)
		{
			if(user.getPassword().equals(password))
			{
				session.setAttribute(roleWithUserRoleService.getRole(user.getId()), user);
				session.setAttribute("user", user);
				mv.addObject(MESSAGE, MESSAGE_SUCCESS);
				UserRole userRole = userRoleService.selectByUserId(user.getId());
				Role role = roleService.selectByPrimaryKey(userRole.getRoleId());
				System.out.println(role.getName());
				switch(role.getName()) {
					case UserConstant.ADMIN:
					case UserConstant.TEACHER:
						mv = new ModelAndView(PageUrlConstant.TEACHER_ADMIN_INDEX);
						break;
					case UserConstant.STUDENT:
						mv = new ModelAndView(PageUrlConstant.STUDENT_INDEX);
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
		ModelAndView mv = new ModelAndView(PageUrlConstant.LOGIN);
		User user = userService.selectByUsername(username);
		if(user!=null)
		{
			user.setPassword(newPassword);
			Integer result = userService.updatePasswordByUsernameSelective(user);
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
		ModelAndView mv = new ModelAndView(PageUrlConstant.INDEX);
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
			mv = new ModelAndView(PageUrlConstant.LOGIN);
		}
		return mv;
	}
	
}
