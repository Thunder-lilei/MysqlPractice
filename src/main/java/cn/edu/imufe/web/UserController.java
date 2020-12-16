package cn.edu.imufe.web;

import cn.edu.imufe.po.Role;
import cn.edu.imufe.po.User;
import cn.edu.imufe.po.UserRole;
import cn.edu.imufe.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.constant.PageUrlConstant;
import cn.edu.imufe.constant.UserConstant;
import cn.edu.imufe.service.RoleService;
import cn.edu.imufe.service.UserRoleService;
import cn.edu.imufe.service.UserService;
import cn.edu.imufe.util.UserUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lilei
 * 2020年11月25日下午5:07:35
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	private final UserService userService;
	private final UserRoleService userRoleService;
	private final RoleService roleService;
	@Autowired
	public UserController(UserService userService, UserRoleService userRoleService,
						  RoleService roleService) {
	this.userService = userService;
	this.userRoleService = userRoleService;
	this.roleService = roleService;
	}

	private final String MESSAGE = "message";
	private final String MESSAGE_SUCCESS = "success"	;
	
	/*
	 * @Author 李雷
	 * @Description
	 * 管理员登陆 保存登录信息至session 跳转至主页
	 * @CreateDate
	 * @UpdateDate 14:15 2020/12/10
	 * @Param [username, password]
	 * @return org.springframework.web.servlet.ModelAndView
	 **/
	@ResponseBody
	@RequestMapping(value="/toLogin",method=RequestMethod.POST)
	private ModelAndView toLogin(@RequestParam String username,@RequestParam String password){
		ModelAndView mv = new ModelAndView(PageUrlConstant.INDEX);
		User user = userService.selectByUsername(username);
		if(user!=null)
		{
			if(BCrypt.checkpw(password,user.getPassword()))
			{
				session.setAttribute(userRoleService.getRole(user.getId()), user);
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
	/*
	 * @Author 李雷
	 * @Description
	 * 修改密码 跳转至登录页面
	 * @CreateDate 14:16 2020/12/10
	 * @UpdateDate 14:16 2020/12/10
	 * @Param [username, newPassword]
	 * @return org.springframework.web.servlet.ModelAndView
	 **/
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
	/*
	 * @Author 李雷
	 * @Description
	 * 登出 跳转至主页
	 * @CreateDate 14:17 2020/12/10
	 * @UpdateDate 14:17 2020/12/10
	 * @Param []
	 * @return org.springframework.web.servlet.ModelAndView
	 **/
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
	/*
	 * @Author 李雷
	 * @Description
	 * 添加用户且为用户授权
	 * 用户不能重名 不能重复授权
	 * @CreateDate 15:52 2020/12/16
	 * @UpdateDate 15:52 2020/12/16
	 * @Param [user, roleId]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	private Map<String,Object> addUser(User user,@RequestParam Long roleId){
		Map<String,Object> modelMap = new HashMap<>();
		if (userService.addUser(user).equals(0)) {
			modelMap.put(MESSAGE,"用户名重复！");
			return modelMap;
		}
		user = userService.selectByUsername(user.getUsername());
		if (userRoleService.addUserRole(user.getId(),roleId).equals(0)) {
			modelMap.put(MESSAGE,"用户添加成功！但已有该权限！");
			return modelMap;
		}
		modelMap.put(MESSAGE,"用户添加成功！");
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 更新用户信息
	 * 不能重名
	 * @CreateDate 16:24 2020/12/16
	 * @UpdateDate 16:24 2020/12/16
	 * @Param [user, roleId]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	private Map<String,Object> updateUser(User user,@RequestParam Long roleId){
		Map<String,Object> modelMap = new HashMap<>();
		if (userService.updateUser(user).equals(0)) {
			modelMap.put(MESSAGE,"更新失败！尝试更换用户名！");
			return modelMap;
		}
		userRoleService.addUserRole(user.getId(),roleId);
		modelMap.put(MESSAGE,"更新成功！");
		return modelMap;
	}
	
}
