package com.earn.helper.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * tree
 * 
 * @author luhui
 * @since 2019/2/1 上午9:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tree<T> {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 显示节点文本
     */
    private String text;
    /**
     * 节点状态，open closed
     */
    private Map<String, Object> state;
    /**
     * 节点是否被选中 true false
     */
    private boolean checked = false;
    /**
     * 节点属性
     */
    private Map<String, Object> attributes;

    /**
     * 节点的子节点
     */
    private List<Tree<T>> children = new ArrayList<Tree<T>>();

    /**
     * 父ID
     */
    private String parentId;
    /**
     * 是否有父节点
     */
    private boolean hasParent = false;
    /**
     * 是否有子节点
     */
    private boolean hasChildren = false;
    /**
     * 图标
     */
    private String icon;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}