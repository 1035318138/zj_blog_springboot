package cn.zj.controller;

import cn.zj.dto.Result;
import cn.zj.entity.User;
import cn.zj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Sept.05 on 2019/8/6.
 *
 * 文件上传
 */
@RestController
@RequestMapping("/api")
public class UploadController {
	@Autowired
	UserService userService;

	@RequestMapping("/upload")
	public Result upload(Long id, MultipartFile uploadfile, HttpServletRequest request){
//		String path = request.getSession().getServletContext().getRealPath("/upload/");
		String classpath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		File file = new File(classpath, "/static/upload");
		if(!file.exists()){
			file.mkdirs();
		}
//		System.out.println(uploadfile);
		//获取文件名
		String fileName = uploadfile.getOriginalFilename();
		fileName = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
		System.out.println(file.getAbsolutePath());
		//上传
		try {
			File file1 = new File(file.getAbsolutePath(), fileName);
			uploadfile.transferTo(file1);
			System.out.println(file1.getName());
//			User user = new User();
//			user.setId(id);
//			user.setAvatar(file1.getName());
			return new Result(200, "上传成功");
		} catch (IOException e) {
			e.printStackTrace();
			return new Result(500, "上传失败");
		}
	}
}
