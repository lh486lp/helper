package com.earn.helper.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章通告表
 * </p>
 *
 * @author luhui
 * @since 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article extends BaseEntity<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 图片地址
     */
    private String imgPath;

    /**
     * 类型 1-文章 2-公告 3-首页banner 4-商品banner 5-消息
     */
    private Integer articleType;

    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

    @Override
    public String toString() {
        return "Article{" + "articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleContent="
            + articleContent + ", imgPath=" + imgPath + ", atticleType=" + articleType + ", visible=" + getVisible()
            + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime() + "}";
    }
}
