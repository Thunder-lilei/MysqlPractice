package cn.edu.imufe.util;

import javax.servlet.http.HttpSession;

/**
 * @author lilei
 *
 * 2020年11月20日
 */
public class UserUtil {
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
}
