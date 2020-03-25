package com.earn.helper.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.earn.common.util.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.earn.helper.entity.Tree;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.system.mapper.UserinfoDao;
import com.earn.helper.system.service.ISystemUserinfoService;

@Service
public class SystemUserinfoServiceImpl implements ISystemUserinfoService {
    @Autowired
    private UserinfoDao userinfoDao;

    @Override
    public Userinfo get(Integer userId) {
        return userinfoDao.get(userId);
    }

    @Override
    public List<Userinfo> list(Map<String, Object> map) {
        return userinfoDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userinfoDao.count(map);
    }

    @Override
    public int save(Userinfo userinfo) {
        return userinfoDao.save(userinfo);
    }

    @Override
    public int update(Userinfo userinfo) {
        return userinfoDao.update(userinfo);
    }

    @Override
    public int remove(Integer userId) {
        return userinfoDao.remove(userId);
    }

    @Override
    public int batchRemove(Integer[] userIds) {
        return userinfoDao.batchRemove(userIds);
    }

    @Override
    public List<Tree<Userinfo>> getFansTree(Integer userId) {
        List<Tree<Userinfo>> trees = new ArrayList<Tree<Userinfo>>();
        List<Userinfo> list = userinfoDao.list(new HashMap<>(16));
        for (Userinfo userinfo : list) {
            Tree<Userinfo> tree = new Tree<Userinfo>();
            tree.setId(userinfo.getUserId().toString());
            tree.setParentId(userinfo.getParentId().toString());
            tree.setText(userinfo.getNickName());
            tree.setIcon("fa fa-user");
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("vipLevel", userinfo.getVipLevel());
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        return BuildTree.buildFansList(trees, userId.toString());
    }
}
