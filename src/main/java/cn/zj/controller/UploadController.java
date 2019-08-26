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
 * <p>
 * 文件上传
 */
@RestController
@RequestMapping("/api")
public class UploadController {
	@Autowired
	UserService userService;

	@RequestMapping("/upload")
	public Result upload(MultipartFile uploadfile, HttpServletRequest request) {
//		String path = request.getSession().getServletContext().getRealPath("/upload/");
		String classpath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		File file = new File(classpath, "/static/upload");
		if (!file.exists()) {
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
			return new Result(200, "http://localhost:8080/upload/" + file1.getName());
		} catch (IOException e) {
			e.printStackTrace();
			return new Result(500, "上传失败");
		}
	}

	@RequestMapping("/upload/article_picture")
	public Result uploadArticlePicture(Long id, MultipartFile uploadfile, HttpServletRequest req) throws IOException {
		//获得静态资源路径
		String classpath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		//以用户id为名创建文件夹
		File articleDir = new File(classpath, "/static/upload/article/" + id);
		if (!articleDir.exists()) {
			articleDir.mkdirs();
		}
		String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + uploadfile.getOriginalFilename();
		File file = new File(articleDir.getAbsolutePath(), fileName);
		uploadfile.transferTo(file);
		return new Result(200, "http://localhost:8080/upload/article/" + id + "/" + file.getName());
	}

	@RequestMapping("/upload/user_avatar")
	public Result uploadAvatar(Long id, MultipartFile uploadfile) throws IOException {
		System.out.println(id);
		System.out.println(uploadfile);
//		return null;
		String classpath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		File userDir = new File(classpath, "/static/user_avatar/" + id);
		if(!userDir.exists()){
			userDir.mkdirs();
		}
		String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + uploadfile.getOriginalFilename();
		File file = new File(userDir.getAbsolutePath(), fileName);
		uploadfile.transferTo(file);
		System.out.println(classpath);
		return new Result(200, "http://localhost:8080/user_avatar/" + id + "/" + file.getName());
	}
}
