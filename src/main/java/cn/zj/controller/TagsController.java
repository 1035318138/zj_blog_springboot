package cn.zj.controller;

import cn.zj.dto.Result;
import cn.zj.entity.Tags;
import cn.zj.enums.ResultEnums;
import cn.zj.service.TagsService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@RestController
@RequestMapping("/api")
public class TagsController {
	@Autowired
	TagsService tagsService;

	@GetMapping("/tags/findAllCount")
	public Result findAllCount(){
		return new Result(200, tagsService.findAllCount());
	}

	@GetMapping("/tags/findAll")
	public Result findAll(){
		return new Result(200, tagsService.findAll());
	}

//	@GetMapping("/tags/findByPage")
//	public Result findByPage(Tags tags, Integer pageCode, Integer pageSize){
//		if(pageCode != null && pageSize != null){
//			return new Result(200, tagsService.findByPage(tags, pageCode, pageSize));
//		}
//		return new Result(400, ResultEnums.ERROR);
//	}

	@GetMapping("/tags/{id}")
	public Result findById(@PathVariable("id")Long id){
		if(id != null){
			Tags tags = tagsService.findById(id);
			if(tags != null)
				return new Result(200, tags);
			return new Result(404, "Not Found");
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@PostMapping("/tags")
	public Result add(Tags tags) throws UnknownHostException {
		if(tags != null){
			tagsService.add(tags);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@PutMapping("/tags")
	public Result update(Tags tags){
		if(tags != null){
			tagsService.update(tags);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@DeleteMapping("/tags/{id}")
	public Result delete(@PathVariable("id")Long... ids){
		if(ids != null){
			tagsService.delete(ids);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}
}
