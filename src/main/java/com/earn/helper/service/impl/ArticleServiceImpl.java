package com.earn.helper.service.impl;

import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import com.earn.helper.entity.Accountinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Article;
import com.earn.helper.mapper.ArticleMapper;
import com.earn.helper.service.IArticleService;

import java.util.List;

/**
 * <p>
 * 文章通告表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Page<Article> pageList(Article article) {
        Page<Article> page = new Page<>(article.getPage(), article.getSize());
        List<Article> records = articleMapper.selectListPage(page, article);
        for (Article record : records) {
            if (!StringUtil.isNullAndBlank(record.getImgPath())) {
                record.setImgPath(Const.FILE_PATH + record.getImgPath());
            }
        }
        return page.setRecords(records);
    }
}
