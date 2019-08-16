package cn.zj.service.impl;

import cn.zj.dto.PageBean;
import cn.zj.entity.ArticleCategory;
import cn.zj.mapper.ArticleCategoryMapper;
import cn.zj.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@Service
@Transactional
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
	@Autowired
	ArticleCategoryMapper articleCategoryMapper;

	@Override
	public void deleteByArticleId(Long article_id) {
		articleCategoryMapper.deleteByArticleId(article_id);
	}

	@Override
	public void deleteByCategoryId(Long category_id) {
		articleCategoryMapper.deleteByCategoryId(category_id);
	}

	@Override
	public Long findAllCount() {
		return null;
	}

	@Override
	public List<ArticleCategory> findAll() {
		return null;
	}

	public PageBean findByPage(Integer pageCode, Integer pageSize) {
		return null;
	}

	@Override
	public ArticleCategory findById(Long id) {
		return null;
	}

	@Override
	public void add(ArticleCategory articleCategory) {
		if (!articleCategoryMapper.exists(articleCategory.getArticle_id(), articleCategory.getCategory_id()))
			articleCategoryMapper.add(articleCategory);
	}

	@Override
	public void update(ArticleCategory articleCategory) {

	}

	@Override
	public void delete(Long... ids) {

	}
}
