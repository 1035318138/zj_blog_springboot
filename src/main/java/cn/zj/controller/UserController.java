package cn.zj.controller;

import cn.zj.dto.Result;
import cn.zj.entity.User;
import cn.zj.enums.ResultEnums;
import cn.zj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/4.
 */
@RestController
@RequestMapping("/api")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

//	@PostMapping("/user/findByPage")
//	public Result findPage(User user, Integer pageCode, Integer pageSize) {
//		if (pageCode != null && pageSize != null) {
//			return new Result(200, userService.findByPage(user, pageCode, pageSize));
//		} else {
//			return new Result(400, null);
//		}
//	}

	@GetMapping("/user/{id}")
	public Result findById(@PathVariable("id") Long id) {
		if (id != null) {
			User user = userService.findById(id);
			if (user != null) {
				return new Result(200, user);
			}
			return new Result(404, "Not Found");
		}
		return new Result(400, null);
	}

	@PostMapping("/user")
	public Result add(User user) {
		if (user != null) {
//			System.out.println(user);
			userService.add(user);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@PutMapping("/user")
	public Result update(User user) {
		if (user != null) {
			userService.update(user);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@DeleteMapping("/user/{id}")
	public Result delete(@PathVariable("id") Long... ids) {
		if (ids != null) {
			userService.delete(ids);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@GetMapping("/user/findByName")
	public Result findByName(String name){
		if(name != null){
			User user = userService.findByName(name);
			if(user != null){
				return new Result(200, user);
			}else{
				return  new Result(404, "Not found");
			}
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}
}
