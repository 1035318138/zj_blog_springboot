package cn.zj.service.impl;

import cn.zj.dto.PageBean;
import cn.zj.entity.Article;
import cn.zj.entity.Category;
import cn.zj.mapper.ArticleCategoryMapper;
import cn.zj.mapper.CategoryMapper;
import cn.zj.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	ArticleCategoryMapper articleCategoryMapper;

	@Override
	public Long findAllCount() {
		return null;
	}

	@Override
	public List<Category> findAll() {
		return categoryMapper.findAll();
	}

	public PageBean findByPage( Integer pageCode, Integer pageSize) {
//		PageHelper.startPage(pageCode, pageSize);
//		Page page = categoryMapper.findByPage(category);
//		return new PageBean(page.getTotal(), page.getResult());
		return null;
	}

	@Override
	public Category findById(Long id) {
		return categoryMapper.findById(id);
	}

	@Override
	public void add(Category category) {
		categoryMapper.add(category);
	}

	@Override
	public void update(Category category) {
		categoryMapper.update(category);
	}

	@Override
	public void delete(Long... ids) {
		for(Long id : ids){
			categoryMapper.delete(id);
			articleCategoryMapper.deleteByCategoryId(id);
		}
	}

	@Override
	public Category findByName(String name) {
		return categoryMapper.findByName(name);
	}

	@Override
	public Category findCategoryByArticleId(Long id) {
		return categoryMapper.findCategoryByArticleId(id);
	}

	@Override
	public Boolean exists(String name) {
		return categoryMapper.exists(name);
	}

	@Override
	public PageInfo<Article> findArticleByCategory(Long id, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(categoryMapper.findArticleByCategory(id));
	}
}
