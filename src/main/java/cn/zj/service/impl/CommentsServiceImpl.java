package cn.zj.service.impl;

import cn.zj.dto.CommentsDTO;
import cn.zj.dto.PageBean;
import cn.zj.entity.Comments;
import cn.zj.entity.User;
import cn.zj.mapper.CommentsMapper;
import cn.zj.mapper.UserMapper;
import cn.zj.service.CommentsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 */
@Service
@Transactional
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	CommentsMapper commentsMapper;
	@Autowired
	UserMapper userMapper;

	@Override
	public Long findAllCount() {
		return commentsMapper.findAllCount();
	}

	@Override
	public List<Comments> findAll() {
		return commentsMapper.findAll();
	}

	public PageBean findByPage(Integer pageCode, Integer pageSize) {
//		PageHelper.startPage(pageCode, pageSize);
//		Page page = commentsMapper.findByPage(comments);
//		return new PageBean(page.getTotal(), page.getResult());
		return null;
	}

	@Override
	public Comments findById(Long id) {
		return commentsMapper.findById(id);
	}

	@Override
	public void add(Comments comments) {
		User user = userMapper.findById(comments.getAuthor_id());
//		comments.setAuthor_id(comments.getAuthor_id());
		comments.setAuthor_avatar(user.getAvatar());
		comments.setState("正常");
		System.out.println(comments);
		commentsMapper.add(comments);
	}

	@Override
	public void update(Comments comments) {
		commentsMapper.update(comments);
	}

	@Override
	public void delete(Long... ids) {
		for(Long id : ids) {
			commentsMapper.delete(id);
		}
	}

	@Override
	public PageBean findCommentsList(Integer pageCode, Integer pageSize, Long articleId, Long sort) {
		PageHelper.startPage(pageCode, pageSize);
		Page<Comments> page = commentsMapper.findAllId(articleId, sort);
		List<Comments> list = commentsMapper.findCommentsList(articleId, sort);
		List<CommentsDTO> commentsDTOlist = new ArrayList<>();
		/**
		 * 封装结果类型结构：
		 *      [{{Comments-Parent}, [{Comments-Children}, {Comments-Children}...]}, {{}, [{}, {}, {}...]}]
		 */

		for(Comments comments : list){
			List<Comments> commentsList = new ArrayList<>();
			if(comments.getP_id() == 0 && comments.getC_id() == 0){
				//这是文章顶级的留言信息
				for(Comments parent : list){
					if(parent.getP_id() != 0){
						if(parent.getP_id() == comments.getId()){
							//说明属于当前父结点
							commentsList.add(parent);
						}
					}
				}
				commentsDTOlist.add(new CommentsDTO(comments, commentsList));
			}
		}
		if (page.getTotal() < (pageCode * pageSize) - 1) {
			return new PageBean(page.getTotal(), commentsDTOlist.subList((pageCode - 1) * pageSize, commentsDTOlist.size()));
		} else {
			return new PageBean(page.getTotal(), commentsDTOlist.subList((pageCode - 1) * pageSize, (pageCode * pageSize) - 1));
		}
	}

	@Override
	public Long findCountByArticle(Long articleId) {
		return commentsMapper.findCountByArticleId(articleId);
	}
}
