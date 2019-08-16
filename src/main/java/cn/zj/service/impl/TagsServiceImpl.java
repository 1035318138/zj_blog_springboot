package cn.zj.service.impl;

import cn.zj.dto.PageBean;
import cn.zj.entity.Tags;
import cn.zj.mapper.ArticleTagsMapper;
import cn.zj.mapper.TagsMapper;
import cn.zj.service.TagsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@Service
@Transactional
public class TagsServiceImpl implements TagsService{
	@Autowired
	TagsMapper tagsMapper;
	@Autowired
	ArticleTagsMapper articleTagsMapper;

	@Override
	public Tags findByName(String name) {
		return tagsMapper.findByName(name);
	}

	@Override
	public List<Tags> findByArticleId(Long id) {
		return tagsMapper.findByArticleId(id);
	}

	@Override
	public Boolean exists(String name) {
		return tagsMapper.exists(name);
	}

	@Override
	public Long findAllCount() {
		return tagsMapper.findAllCount();
	}

	@Override
	public List<Tags> findAll() {
		return tagsMapper.findAll();
	}

	public PageBean findByPage(Integer pageCode, Integer pageSize) {
//		PageHelper.startPage(pageCode, pageSize);
//		Page page = tagsMapper.findByPage(tags);
//		return new PageBean(page.getTotal(), page.getResult());
		return null;
	}

	@Override
	public Tags findById(Long id) {
		return tagsMapper.findById(id);
	}

	@Override
	public void add(Tags tags) {
		tagsMapper.add(tags);
	}

	@Override
	public void update(Tags tags) {
		tagsMapper.update(tags);
	}

	@Override
	public void delete(Long... ids) {
		for(Long id : ids){
			tagsMapper.delete(id);
			articleTagsMapper.deleteByTagsId(id);
		}
	}
}
