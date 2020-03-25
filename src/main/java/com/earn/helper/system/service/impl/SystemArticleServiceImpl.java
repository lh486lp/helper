package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.ArticleDao;
import com.earn.helper.entity.Article;
import com.earn.helper.system.service.ISystemArticleService;



@Service
public class SystemArticleServiceImpl implements ISystemArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public Article get(Integer articleId){
		return articleDao.get(articleId);
	}
	
	@Override
	public List<Article> list(Map<String, Object> map){
		return articleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return articleDao.count(map);
	}
	
	@Override
	public int save(Article article){
		return articleDao.save(article);
	}
	
	@Override
	public int update(Article article){
		return articleDao.update(article);
	}
	
	@Override
	public int remove(Integer articleId){
		return articleDao.remove(articleId);
	}
	
	@Override
	public int batchRemove(Integer[] articleIds){
		return articleDao.batchRemove(articleIds);
	}
	
	
}
