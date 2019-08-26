package cn.zj.service.impl;

import cn.zj.entity.*;
import cn.zj.mapper.*;
import cn.zj.service.ArticleService;
import cn.zj.service.CategoryService;
import cn.zj.service.TagsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleCategoryMapper articleCategoryMapper;
	@Autowired
	ArticleTagsMapper articleTagsMapper;
	@Autowired
	DateCountMapper dateCountMapper;
	@Autowired
	CommentsMapper commentsMapper;
	@Autowired
	UserMapper userMapper;
//	@Autowired
//	CategoryService categoryService;
	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public Long findAllCount() {
		return articleMapper.findAllCount();
	}

	public List<Article> findAll() {
		return articleMapper.findAll();
	}

	public PageInfo<Article> findByPage(Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
			List<Article> articles = articleMapper.findAll();
			PageInfo<Article> pageInfo = new PageInfo<>(articles);
			pageInfo.getList().forEach(article -> article.setCategory(categoryMapper.findCategoryByArticleId(article.getId()).getName()));
			return pageInfo;
		}
		return null;
	}

	@Override
	public Article findById(Long id) {
		Article article = articleMapper.findById(id);
		initArticle(article);
		return article;
	}

	@Override
	public void add(Article article) {
		article.setPublish_time(new Date());
		article.setEdit_time(new Date());
		article.setEye_count(0);
		articleMapper.add(article);
//		System.out.println(article);
		addCategoryAndTags(article);//更新关联表信息
	}

	@Override
	public void update(Article article) {
//		System.out.println(article);
		articleMapper.update(article);
		updateCategoryAndTags(article);
	}

	@Override
	public void delete(Long... ids) {
		for (Long id : ids) {
//			System.out.println("delete..." + id);
			articleMapper.deleteById(id);
			//更新关联表
			articleTagsMapper.deleteByArticleId(id);
			articleCategoryMapper.deleteByArticleId(id);
		}
	}

	/**
	 * 每天保存文章数量的数据
	 */
	@Scheduled(cron = "0 0 0 * * MON-FRI")//定时每一天凌晨统计   秒分时天月 星期
	public void saveCountByDay() {
		Long articleCount = articleMapper.findAllCount();
		Long eyeCount = articleMapper.findAllEyeCount();
		Long comments = commentsMapper.findAllCount();
		Long user_count = userMapper.findUserCount();
		dateCountMapper.add(new DateCount(null, new Date(), articleCount, eyeCount, comments, user_count));
	}

	public List<Long> findArticleCount(){
		return dateCountMapper.findArticleCount();
	}

	public List<Long> findEyeCount(){
		return dateCountMapper.findEyeCount();
	}

	public List<String> findWeek(){
		return dateCountMapper.findWeek();
	}

	public List<Long> findCommentCount(){
		return dateCountMapper.findCommentCount();
	}

	public List<Long> findUserCount(){
		return dateCountMapper.findUserCount();
	}

	@Override
	public List<Article> findByCategory(String category) {
		return articleMapper.findByCategory(category);
	}

	@Override
	public PageInfo<Article> findArchivesByDate(String date, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(articleMapper.findArchivesByDate(date));
	}

	public List<String> findArchivesDates(){
		return articleMapper.findArchivesDates();
	}

	@Override
	public List<Article> findFuzzyByTitle(String title) {
		return articleMapper.findFuzzyByTitle(title);
	}

	@Override
	public void addEyeCount(Long id) {
		articleMapper.addEyeCount(id);
	}

	/**
	 * 初始化文章，设置文章的分类和标签
	 *
	 * @param articles
	 */
	private void initArticle(List<Article> articles) {
		for(Article article : articles){
			article.setCategory(categoryMapper.findCategoryByArticleId(article.getId()).getName());
		}
		articles.forEach(System.out::println);
//		for (Article article : articles) {
//			List<Category> categories = categoryService.findCategoryByArticleId(article.getId());
//			if (!categories.isEmpty()) {
//				article.setCategory(categories.get(0).getName());
//			}
//			List<Tags> tags = tagsService.findByArticleId(article.getId());
//			List<String> singleTagName = new ArrayList<>();

//			if (!tags.isEmpty()) {
//				for (Tags tag : tags) {
//					singleTagName.add(tag.getName());
//				}
//				article.setTags(JSON.toJSONString(singleTagName));
//			}
//		}
	}

	private void initArticle(Article article) {
		Category category = categoryMapper.findCategoryByArticleId(article.getId());
		article.setCategory(category.getName());
//		List<Category> categorys = categoryService.findCategoryByArticleId(article.getId());
//		if (!categorys.isEmpty()) {
//			article.setCategory(categorys.get(0).getName());
//		}
//		List<Tags> tags = tagsService.findByArticleId(article.getId());
//		List<String> singleTagName = new ArrayList<>();
//		if (!tags.isEmpty()) {
//			for (Tags tag : tags) {
//				singleTagName.add(tag.getName());
//			}
//		}
//		article.setTags(JSON.toJSONString(singleTagName));
//		article.setTags(singleTagName.toString());
	}

	/**
	 * 更新文章分类和标签
	 *
	 * @param article
	 */
	private void updateCategoryAndTags(Article article) {
		if (article.getCategory() != null) {//新增文章的分类不为空
			articleCategoryMapper.updateByAritlceId(categoryMapper.findByName(article.getCategory()).getId(), article.getId());//更新文章分类关联表信息
		}
	}

	private void addCategoryAndTags(Article article){
		if(article.getCategory() != null){
			articleCategoryMapper.add(new ArticleCategory(article.getId(), categoryMapper.findByName(article.getCategory()).getId()));
		}
	}
}
