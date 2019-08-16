package cn.zj.dto;

import cn.zj.entity.Article;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sept.05 on 2019/8/6.
 *
 * 用于时间归档
 */
public class ArticleArchives implements Serializable{
	private String date;
	private List<Article> articles;

	public ArticleArchives() {
	}

	public ArticleArchives(String date, List<Article> articles) {
		this.date = date;
		this.articles = articles;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "ArticleArchives{" +
				"date='" + date + '\'' +
				", articles=" + articles +
				'}';
	}
}
