package cn.zj.controller;

import cn.zj.dto.Result;
import cn.zj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sept.05 on 2019/8/7.
 */
@RestController
public class LoginController {
	@Autowired
	UserService userService;

	@GetMapping("/userLogin")
	public String loginPage(){
		return "login";
	}

	@RequestMapping("/refuce")
	public  Result refucePage(){
		return new Result(403, "无权访问");
	}

	@RequestMapping("/success")
	public Result success(){
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new Result(200, userService.findByName(user.getUsername()));
	}

	@RequestMapping("/fail")
	public Result fail(){
		return new Result(400, "用户名或密码错误");
	}

	@RequestMapping("/logoutSuccess")
	public Result logout(){
		return new Result(200, "注销成功");
	}
}
