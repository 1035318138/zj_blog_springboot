package cn.zj.controller;

import cn.zj.dto.Result;
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
	@RequestMapping("/upload")
	public Result upload(MultipartFile uploadfile, HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//获取文件名
		String fileName = uploadfile.getOriginalFilename();
		fileName = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
		//上传
		try {
			uploadfile.transferTo(new File(path, fileName));
			return new Result(200, "上传成功");
		} catch (IOException e) {
			e.printStackTrace();
			return new Result(500, "上传失败");
		}
	}
}
