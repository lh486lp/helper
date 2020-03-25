package com.earn.helper.system.mapper;

import com.earn.helper.entity.Article;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 文章通告表
 * luhui
 * @date 2019-02-11 10:56:11
 */
@Mapper
public interface ArticleDao {

	Article get(Integer articleId);
	
	List<Article> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Article article);
	
	int update(Article article);
	
	int remove(Integer article_id);
	
	int batchRemove(Integer[] articleIds);
	
	
}
