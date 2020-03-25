package com.earn.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.earn.helper.entity.Tree;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 卢惠
 * @since 2019/2/3 上午10:27
 */
@Slf4j
public class BuildTree {
    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        return build(nodes, "0");
    }

    public static <T> Tree<T> build(List<Tree<T>> nodes, String idParam) {
        if (nodes == null) {
            return null;
        }
        String topText = "";
        List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
        for (Tree<T> children : nodes) {
            String pid = children.getParentId();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(children);
                continue;
            }
            if (idParam.equals(children.getId())) {
                topText = children.getText();
            }
            buildChildren(nodes, children, pid);
        }
        Tree<T> root = new Tree<>();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root.setId("-1");
            root.setParentId("");
            root.setHasParent(false);
            root.setHasChildren(true);
            root.setChecked(true);
            root.setChildren(topNodes);
            root.setText("0".equals(idParam) ? "顶级节点" : topText);
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            root.setState(state);
        }

        return root;
    }

    public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
        for (Tree<T> children : nodes) {
            String pid = children.getParentId();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(children);
                continue;
            }
            buildChildren(nodes, children, pid);
        }
        return topNodes;
    }

    private static <T> void buildChildren(List<Tree<T>> nodes, Tree<T> children, String pid) {
        for (Tree<T> parent : nodes) {
            String id = parent.getId();
            if (id != null && id.equals(pid)) {
                parent.getChildren().add(children);
                children.setHasParent(true);
                parent.setHasChildren(true);
            }
        }
    }

    private static <T> void buildFansList(List<Tree<T>> nodes){
        for (Tree<T> children : nodes) {
            List<Tree<T>> sons = children.getChildren();
            for (Tree<T> grandson : sons) {
                grandson.setHasChildren(false);
                grandson.setChildren(null);
            }
            children.setText(children.getText() + "（"+sons.size()+"）");
        }
    }

    public static <T> List<Tree<T>> buildFansList(List<Tree<T>> nodes, String idParam) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> normalNodes = new ArrayList<Tree<T>>();
        List<Tree<T>> goldNodes = new ArrayList<Tree<T>>();
        List<Tree<T>> diamondNodes = new ArrayList<Tree<T>>();
        for (Tree<T> children : nodes) {
            Integer vipLevel = StringUtil.numFormat(children.getState().get("vipLevel"));
            String pid = children.getParentId();
            if (pid == null || idParam.equals(pid)) {
                switch (vipLevel) {
                    case 1:
                        normalNodes.add(children);
                        break;
                    case 2:
                        goldNodes.add(children);
                        break;
                    case 3:
                        diamondNodes.add(children);
                        break;
                    default:
                        break;
                }
                continue;
            }
            buildChildren(nodes, children, pid);
        }
        buildFansList(normalNodes);
        buildFansList(goldNodes);
        buildFansList(diamondNodes);

        Map<String, Object> state = new HashMap<>(16);
        state.put("opened", true);

        Tree<T> normalRoot = new Tree<>();
        normalRoot.setId("-1");
        normalRoot.setParentId("");
        normalRoot.setHasParent(false);
        normalRoot.setHasChildren(true);
        normalRoot.setChecked(true);
        normalRoot.setChildren(normalNodes);
        normalRoot.setText("普通会员（"+normalNodes.size()+"）");
        normalRoot.setIcon("fa fa-group");
        normalRoot.setState(state);

        Tree<T> goldRoot = new Tree<>();
        goldRoot.setId("-2");
        goldRoot.setParentId("");
        goldRoot.setHasParent(false);
        goldRoot.setHasChildren(true);
        goldRoot.setChecked(true);
        goldRoot.setChildren(goldNodes);
        goldRoot.setText("金牌会员（"+goldNodes.size()+"）");
        goldRoot.setIcon("fa fa-group");
        goldRoot.setState(state);

        Tree<T> diamondRoot = new Tree<>();
        diamondRoot.setId("-3");
        diamondRoot.setParentId("");
        diamondRoot.setHasParent(false);
        diamondRoot.setHasChildren(true);
        diamondRoot.setChecked(true);
        diamondRoot.setChildren(diamondNodes);
        diamondRoot.setText("钻石会员（"+diamondNodes.size()+"）");
        diamondRoot.setIcon("fa fa-group");
        diamondRoot.setState(state);


        List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
        topNodes.add(normalRoot);
        topNodes.add(goldRoot);
        topNodes.add(diamondRoot);
        return topNodes;
    }
}