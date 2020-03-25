package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Article;

/**
 * <p>
 * 文章通告表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface IArticleService extends IService<Article> {

    /**
     * 分页查询列表
     *
     * @param article
     * @return
     */
    Page<Article> pageList(Article article);
}
