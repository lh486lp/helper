package com.earn.helper.controller;

import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import com.earn.helper.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Article;
import com.earn.helper.entity.R;
import com.earn.helper.service.IArticleService;

/**
 * <p>
 * 文章通告表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    private IArticleService articleService;

    @ResponseBody
    @GetMapping("/list")
    public R list(Article article) {
        Page<Article> list = articleService.pageList(article);
        return new R<Page<Article>>().setData(list);
    }

    @ResponseBody
    @GetMapping("/get/{id}")
    public R list(@PathVariable("id") Integer id) {
        Article article = new Article().selectById(id);
        if (!StringUtil.isNullAndBlank(article.getImgPath())) {
            article.setImgPath(Const.FILE_PATH + article.getImgPath());
        }
        return new R<Article>().setData(article);
    }

    @GetMapping("/advertising")
    public String advertising() {
        return "helper/advertising";
    }

    @GetMapping("/busiSchool")
    public String businessSchool() {
        return "helper/BusinessSchool";
    }
}
