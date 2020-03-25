package com.earn.helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Article;

/**
 * <p>
 * 文章通告表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectListPage(@Param("page") Page<Article> page, @Param("article") Article article);
}