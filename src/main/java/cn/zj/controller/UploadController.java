package cn.zj.controller;

import cn.zj.dto.Result;
import cn.zj.entity.User;
import cn.zj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
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
	@Value("${uploadFile.resourceHandler}")
	private String resourceHandler;

	@Value("${uploadFile.location}")
	private String uploadFileLocation;

	@PostMapping("/upload")
	public Result upload(MultipartFile uploadfile, HttpServletRequest request) throws IOException {
		if(uploadfile == null || uploadfile.isEmpty()){
			return new Result(400, "Bad Request");
		}
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + uploadfile.getOriginalFilename();
		String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + fileName;
		System.out.println("访问路径："+fileServerPath);
		File saveFile = new File(uploadFileLocation, fileName);
		uploadfile.transferTo(saveFile);
		System.out.println("保存路径：" + saveFile.getPath());
		return new Result(200, fileServerPath);
	}

	@PostMapping("/upload/user_avatar")
	public Result uploadAvatar(Long id, MultipartFile uploadfile, HttpServletRequest req) throws IOException {
		if(uploadfile == null || uploadfile.isEmpty()){
			return new Result(400, "Bad Request");
		}
		String path = uploadFileLocation + "user/" + id + "/";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		String fileName = uploadfile.getOriginalFilename();
		String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + "user/" + id + "/" + fileName;
		System.out.println("访问路径：" + fileServerPath);

		File saveFile = new File(path, fileName);
		uploadfile.transferTo(saveFile);
		System.out.println("保存路径：" + saveFile.getPath());
		return new Result(200, fileServerPath);
	}

	@RequestMapping("/upload/article_picture")
	public Result uploadArticlePicture(Long id, MultipartFile uploadfile, HttpServletRequest req) throws IOException {
		String path = uploadFileLocation + "article/" + id + "/";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		String base = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		String fileName = uploadfile.getOriginalFilename();
		String fileServicePath = base + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + "article/" + id + "/" + fileName;
		File saveFile = new File(path, fileName);
		uploadfile.transferTo(saveFile);
		System.out.println("访问路径：" + fileServicePath);
		System.out.println("保存路径：" + saveFile.getPath());
		return new Result(200, fileServicePath);
	}
}
