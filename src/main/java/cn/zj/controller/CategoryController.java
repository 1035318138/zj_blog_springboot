package cn.zj.controller;

import cn.zj.dto.Result;
import cn.zj.entity.Article;
import cn.zj.entity.Category;
import cn.zj.enums.ResultEnums;
import cn.zj.service.ArticleService;
import cn.zj.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	CategoryService categoryService; 
	@Autowired  
	ArticleService articleService;

	@GetMapping("/category/findAll")
	public Result findAll() {
		return new Result(200, categoryService.findAll());
	}

	/**
	 * 查询所有的分类（包含对应的文章数量），结构：[{分类名称，数量},{},....]
	 *
	 * @return
	 */
	@GetMapping("/category/findArticleCountForCategory")
	public Result findArticleCountForCategory() {
		Map<String, Integer> map = new HashMap<>();
		List<Category> categoryList = categoryService.findAll();
		for (Category category : categoryList) {
			List<Article> articleList = articleService.findByCategory(category.getName());
			map.put(category.getName(), articleList.size());
		}
		return new Result(200, map);
	}

//	@PostMapping("/category/findByPage")
//	public Result findByPage(Category category, Integer pageCode, Integer pageSize) {
//		if (pageCode != null && pageSize != null) {
//			return new Result(200, categoryService.findByPage(category, pageCode, pageSize));
//		}
//		return new Result(400, ResultEnums.ERROR);
//	}

	@GetMapping("/category/{id}")
	public Result findById(@PathVariable("id") Long id) {
		if (id != null) {
			return new Result(200, categoryService.findById(id));
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@PostMapping("/category")
	public Result add(Category category){
		if(category != null){
			categoryService.add(category);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@PutMapping("/category")
	public Result update(Category category){
		if(category != null){
			categoryService.update(category);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@DeleteMapping("/category/{id}")
	public Result delete(@PathVariable("id") Long... ids){
		if(ids != null){
			categoryService.delete(ids);
			return new Result(200, ResultEnums.SUCCESS);
		}
		return new Result(400, ResultEnums.ERROR);
	}

	@GetMapping("/category/findArticleByCategory")
	public PageInfo<Article> findArticleByCategory(Long id, @RequestParam(defaultValue = "1") Integer pageNum,
	                                               @RequestParam(defaultValue = "5") Integer pageSize){
		if(id != null){
			return categoryService.findArticleByCategory(id, pageNum, pageSize);
		}
		System.out.println(id);
		return null;
	}
}
