package com.earn.helper.system.service;

import com.earn.helper.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * 文章通告表
 * 
 * luhui
 * @date 2019-02-11 10:56:11
 */
public interface ISystemArticleService {
	
	Article get(Integer articleId);
	
	List<Article> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Article article);
	
	int update(Article article);
	
	int remove(Integer articleId);
	
	int batchRemove(Integer[] articleIds);
	
}
