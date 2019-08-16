package cn.zj.service.impl;

import cn.zj.dto.PageBean;
import cn.zj.entity.ArticleTags;
import cn.zj.entity.Tags;
import cn.zj.mapper.ArticleTagsMapper;
import cn.zj.service.ArticleTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@Service
@Transactional
public class ArticleTagsServiceImpl implements ArticleTagsService{
	@Autowired
	ArticleTagsMapper articleTagsMapper;

	@Override
	public void deleteByArticleId(Long id) {
		articleTagsMapper.deleteByArticleId(id);
	}

	@Override
	public void deleteByTagsId(Long id) {
		articleTagsMapper.deleteByTagsId(id);
	}

	@Override
	public List<Tags> findByArticleId(Long id) {
		return articleTagsMapper.findByArticleId(id);
	}

	@Override
	public Long findAllCount() {
		return null;
	}

	@Override
	public List<ArticleTags> findAll() {
		return null;
	}

	public PageBean findByPage(Integer pageCode, Integer pageSize) {
		return null;
	}

	@Override
	public ArticleTags findById(Long id) {
		return null;
	}

	@Override
	public void add(ArticleTags articleTags) {
		if(!articleTagsMapper.exists(articleTags.getArticle_id(), articleTags.getTags_id()))
			articleTagsMapper.add(articleTags);
	}

	@Override
	public void update(ArticleTags articleTags) {

	}

	@Override
	public void delete(Long... ids) {

	}
}
