package com.earn.helper.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.Article;
import com.earn.helper.entity.R;
import com.earn.helper.system.service.ISystemArticleService;

/**
 * 文章通告表 luhui
 * 
 * @since 2019-02-11 10:56:11
 */

@Controller
@RequestMapping("/system/article")
public class SystemArticleController {
    @Autowired
    private ISystemArticleService articleService;

    @GetMapping()
    String Article() {
        return "helper/article/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Article> articleList = articleService.list(query);
        int total = articleService.count(query);
        PageUtils pageUtils = new PageUtils(articleList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/article/add";
    }

    @GetMapping("/edit/{articleId}")
    String edit(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleService.get(articleId);
        model.addAttribute("article", article);
        return "helper/article/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Article article) {
        if (articleService.save(article) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Article article) {
        articleService.update(article);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer articleId) {
        if (articleService.remove(articleId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] articleIds) {
        if (articleService.batchRemove(articleIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
