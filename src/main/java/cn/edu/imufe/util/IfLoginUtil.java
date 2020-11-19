package cn.edu.imufe.util;

import javax.servlet.http.HttpSession;

import cn.edu.imufe.entity.Auser;

public class IfLoginUtil {
	public static Boolean IfLogin(HttpSession session) {
		if(session.getAttribute("user")!=null && session.getAttribute("user")!="") {
			return true;
		}
		return false;
	}
	public static Boolean TheRole(String role,HttpSession session) {
		if(session.getAttribute(role)!=null && session.getAttribute(role)!="") {
			return true;
		}
		return false;
	}
	public static String GetRole(HttpSession session) {
		if(IfLogin(session)) {
			Auser record = (Auser)session.getAttribute("user");
			return record.getRole();
		}
		return "未登录";
	}
}
