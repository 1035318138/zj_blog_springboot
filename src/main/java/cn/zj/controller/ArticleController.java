package cn.zj.controller;

import cn.zj.dto.PageBean;
import cn.zj.dto.Result;
import cn.zj.entity.Article;
import cn.zj.entity.Tags;
import cn.zj.enums.ResultEnums;
import cn.zj.service.ArticleCategoryService;
import cn.zj.service.ArticleService;
import cn.zj.service.ArticleTagsService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleCategoryService categoryService;
	@Autowired
	ArticleTagsService articleTagsService;

	@GetMapping("/article/findAll")
	public Result findAll() {
		return new Result(200, articleService.findAll());
	}

	@GetMapping("/article/findAllCount")
	public Result findAllCount() {
		return new Result(200, articleService.findAllCount());
	}

	/**
	 * 根据页码和大小分页查询
	 *
	 * @param pageNum  当前页，默认为1
	 * @param pageSize 当前每页显示行数，默认为5
	 * @return 页信息的实体
	 */
	@GetMapping("/article/findByPage")
	public PageInfo<Article> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
	                                    @RequestParam(defaultValue = "5") Integer pageSize) {
		return articleService.findByPage(pageNum, pageSize);
	}

	@GetMapping("/article/search/{title}")
	public Result findByTitle(@PathVariable("title") String title) {
		if (title != null) {
			List<Article> articleList = articleService.findFuzzyByTitle(title);
			if (articleList != null && !articleList.isEmpty())
				return new Result(200, articleList);
		}
		return new Result(400, ResultEnums.ERROR);
	}

//	@PostMapping("/article/findByPageForSite")
//	public Result findByPageForSite(Integer pageCode, Integer pageSize) {
//		if (pageCode != null && pageSize != null) {
//			PageBean pageBean = articleService.findByPageForSite(pageCode, pageSize);
//			pageBean.setTotal((long) Math.ceil(pageBean.getTotal() / pageSize));
//			return new Result(200, pageBean);
//		}
//		return new Result(400, ResultEnums.ERROR);
//	}

	@GetMapping("/article/{id}")
	public Result findById(@PathVariable("id") Long id) {
		if (id != null) {
			Article article = articleService.findById(id);
			if (article != null) {
//				List<String> tagName = new ArrayList<>();
//				List<Tags> tags = articleTagsService.findByArticleId(id);
//				for (Tags tag : tags) {
//					tagName.add(tag.getName());
//				}
//				article.setTags(JSON.toJSONString(tags));
				return new Result(200, article);
			}
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@PostMapping("/article")
	public Result add(Article article){
		if(article != null){
			articleService.add(article);
			return new Result(200, "success");
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@GetMapping("/article/findTags/{id}")
	public Result findTags(@PathVariable("id") Long id) {
		if (id != null) {
			List<Tags> tags = articleTagsService.findByArticleId(id);
			if (!tags.isEmpty()) {
				return new Result(200, JSON.toJSONString(tags));
			} else {
				return new Result(400, ResultEnums.ERROR);
			}
		}
		return new Result(400, ResultEnums.PARAM_ERROR);
	}

	@GetMapping("/article/findArchivesByDate")
	public PageInfo<Article> findArchives(String date, @RequestParam(defaultValue = "1") Integer pageNum,
	                                                   @RequestParam(defaultValue = "5") Integer pageSize) {
		if(date != null) {
			return articleService.findArchivesByDate(date, pageNum, pageSize);
		}
		return null;
	}

	@GetMapping("/article/findArchivesDates")
	public Result findArchivesTime(){
		return new Result(200, articleService.findArchivesDates());
	}


	@PutMapping("/article")
	public Result update(@RequestBody Article article) {
		if (article != null) {
			System.out.println(article);
			articleService.update(article);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@DeleteMapping("/article/{id}")
	public Result delete(@PathVariable("id") Long... ids) {
		if (ids != null) {
			articleService.delete(ids);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@GetMapping("/article/findArticleCount")
	public Result findArticleCount(){
		return new Result(200, articleService.findArticleCount());
	}

	@GetMapping("/article/findEyeCount")
	public Result findEyeCount(){
		return new Result(200, articleService.findEyeCount());
	}

	@GetMapping("/article/findWeek")
	public Result findWeek(){
		return new Result(200, articleService.findWeek());
	}

	@GetMapping("/article/findCommentCount")
	public Result findCommentCount(){
		return new Result(200, articleService.findCommentCount());
	}

	@GetMapping("/article/findUserCount")
	public Result findUserCount(){
		return new Result(200, articleService.findUserCount());
	}
}
