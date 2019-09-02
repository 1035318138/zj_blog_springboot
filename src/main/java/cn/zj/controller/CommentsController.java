package cn.zj.controller;

import cn.zj.dto.PageBean;
import cn.zj.dto.Result;
import cn.zj.entity.Comments;
import cn.zj.enums.ResultEnums;
import cn.zj.service.CommentsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@RestController
@RequestMapping("/api")
public class CommentsController {
	@Autowired
	CommentsService commentsService;

	@GetMapping("/comments/findAllCount")
	public Result getAllCount() {
		return new Result(200, commentsService.findAllCount());
	}

	@GetMapping("/comments/findAll")
	public Result getAll() {
		return new Result(200, commentsService.findAll());
	}

//	@PostMapping("/comments/findByPage")
//	public Result findByPage(Comments comments, Integer pageCode, Integer pageSize) {
//		if (comments != null && pageCode != null && pageSize != null) {
//			return new Result(200, commentsService.findByPage(comments, pageCode, pageSize));
//		}
//		return new Result(400, ResultEnums.PARAM_ERROR);
//	}

	@GetMapping("/comments/findByPage")
	public PageInfo<Comments> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
	                                     @RequestParam(defaultValue = "5") Integer pageSize) {
		return commentsService.findByPage(pageNum, pageSize);
	}

	@GetMapping("/comments/findCountByArticle/{id}")
	public Result findCountByArticle(@PathVariable("id") Long id) {
		if (id != null) {
			return new Result(200, commentsService.findCountByArticle(id));
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@GetMapping("/comments/findCommentsList")
	public Result findCommentsList(Integer pageCode, Integer pageSize, Long articleId, Long sort) {
		if (pageCode == null)
			pageCode = 1;
		if (pageSize != null && pageCode != null && articleId != null && sort != null) {
			PageBean pageBean = commentsService.findCommentsList(pageCode, pageSize, articleId, sort);
			pageBean.setTotal((long) Math.ceil(pageBean.getTotal() / pageSize));
			return new Result(200, pageBean);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@GetMapping("/comments/{id}")
	public Result findById(@PathVariable("id") Long id) {
		if (id != null) {
			Comments comments = commentsService.findById(id);
			if (comments != null) {
				return new Result(200, comments);
			}
			return new Result(404, "Not Found");
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@PostMapping("/comments")
	public Result add(Comments comments) throws UnknownHostException {
		if (comments != null) {
			commentsService.add(comments);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@PutMapping("/comments")
	public Result update(Comments comments) {
		if (comments != null) {
			commentsService.update(comments);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@DeleteMapping("/comments/{id}")
	public Result delete(@PathVariable("id") Long... ids) {
		if (ids != null) {
			commentsService.delete(ids);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@GetMapping("/comments/findByArticleId")
	public Result findByArticleId(Long id) {
		if (id != null) {
			return new Result(200, commentsService.findByArticleId(id));
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@GetMapping("/comments/findByRecent")
	public Result findByRecent() {
		return new Result(200, commentsService.findByRecent());
	}

	@PutMapping("/comments/updateStatus")
	public Result updateStatus(Comments comments) {
		commentsService.updateStatus(comments);
		return new Result(200, ResultEnums.SUCCESS);
	}

	@GetMapping("/comments/findAllAdmin")
	public PageInfo<Comments> findAllAdmin(Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageSize != null)
			return commentsService.findAllAdmin(pageNum, pageSize);
		return null;
	}
}
