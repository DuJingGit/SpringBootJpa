
package com.itmayiedu.controller;

import com.itmayiedu.realm.AES;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@CacheConfig(cacheNames = "baseCache")
//@RestController
@Controller
@RequestMapping("/login")
public class LoginController {
//	private LogManager logger = LogManager.getLogManager();
//@Autowired
//private CacheManager cacheManager;
//	@RequestMapping("/remoKey")
//	public void remoKey() {
//		cacheManager.getCache("baseCache").clear();
//	}
	@RequestMapping("/shiro-login")
	public String login(HttpServletRequest request,@RequestParam("username") String name, @RequestParam("password") String password){
		System.out.println("dj-------------123");
		HttpSession session = request.getSession();
		session.setAttribute("name",name);
		String cKey = "1234567890123456";
		try {
			password = AES.Encrypt(password,cKey);
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(password);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);

		try {
			subject.login(token);
			//subject.isPermitted("aaaa");
			subject.isPermitted("a");//判断权限()
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			System.out.println("登陆失败: " + ae.getMessage());
			return "/shiro-login";
		}
		return "/welcome";
	}

	@RequestMapping("/toLogin")
	public String toLogin() {
		System.out.println("dj--------------------");
		return "/shiro-login";
	}
	@RequestMapping("/shiro-unauthorized")
	public String unauthorized() {
		return "/shiro-unauthorized";
	}

	@RequestMapping("/shiro-403")
	public String unFind() {
		return "/shiro-403";
	}

}
