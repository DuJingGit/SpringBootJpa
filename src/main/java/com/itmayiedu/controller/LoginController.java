
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
