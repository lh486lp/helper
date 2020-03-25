package com.earn.system.service.impl;

import java.util.*;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.earn.helper.entity.Tree;
import com.earn.common.util.BuildTree;
import com.earn.common.util.EncryptUtils;
import com.earn.common.util.StringUtil;
import com.earn.system.entity.SysDept;
import com.earn.system.entity.SysUser;
import com.earn.system.entity.SysUserRole;
import com.earn.system.mapper.SysDeptMapper;
import com.earn.system.mapper.SysUserMapper;
import com.earn.system.mapper.SysUserRoleMapper;
import com.earn.system.service.ISysDeptService;
import com.earn.system.service.ISysUserService;

@Transactional
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysUserRoleMapper userRoleMapper;
    @Autowired
    SysDeptMapper deptMapper;
    @Autowired
    ISysDeptService deptService;

    @Override
    // @Cacheable(value = "user",key = "#id")
    public SysUser get(Integer id) {
        List<Integer> roleIds = userRoleMapper.listRoleId(id);
        SysUser user = userMapper.get(id);
        user.setDeptName(deptMapper.get(user.getDeptId()).getName());
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public List<SysUser> list(Map<String, Object> map) {
        String deptId = map.get("deptId").toString();
        if (StringUtil.isNotBlank(deptId)) {
            Integer deptIdl = Integer.valueOf(deptId);
            List<Integer> childIds = deptService.listChildrenIds(deptIdl);
            childIds.add(deptIdl);
            map.put("deptId", null);
            map.put("deptIds", childIds);
        }
        return userMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Transactional
    @Override
    public int save(SysUser user) {
        int count = userMapper.save(user);
        Integer userId = user.getUserId();
        List<Integer> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (Integer roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(SysUser user) {
        int r = userMapper.update(user);
        Integer userId = user.getUserId();
        List<Integer> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (Integer roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return r;
    }

    // @CacheEvict(value = "user")
    @Override
    public int remove(Integer userId) {
        userRoleMapper.removeByUserId(userId);
        return userMapper.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Integer userId) {
        return null;
    }

    @Override
    public int resetPwd(SysUser user) throws Exception {
        if (Objects.equals(EncryptUtils.encode(user.getOldPassword()), user.getPassword())) {
            user.setPassword(EncryptUtils.encode(user.getNewPassword()));
            return userMapper.update(user);
        } else {
            throw new Exception("输入的旧密码有误！");
        }
    }

    @Override
    public int adminResetPwd(SysUser user) throws Exception {
        if ("admin".equals(user.getUsername())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        user.setPassword(EncryptUtils.encode(user.getNewPassword()));
        return userMapper.update(user);

    }

    @Transactional
    @Override
    public int batchremove(Integer[] userIds) {
        int count = userMapper.batchRemove(userIds);
        userRoleMapper.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public Tree<SysDept> getTree() {
        List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
        List<SysDept> depts = deptMapper.list(new HashMap<String, Object>(16));
        Integer[] pDepts = deptMapper.listParentDept();
        Integer[] uDepts = userMapper.listAllDept();
        Integer[] allDepts = (Integer[])ArrayUtils.addAll(pDepts, uDepts);
        for (SysDept dept : depts) {
            if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
                continue;
            }
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(dept.getDeptId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<SysUser> users = userMapper.list(new HashMap<String, Object>(16));
        for (SysUser user : users) {
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(user.getUserId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public int updatePersonal(SysUser userDO) {
        return userMapper.update(userDO);
    }

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Integer userId)
        throws Exception {
        // String fileName = file.getOriginalFilename();
        // fileName = FileUtil.renameToUUID(fileName);
        // FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        // // 获取图片后缀
        // String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        // String[] str = avatar_data.split(",");
        // // 获取截取的x坐标
        // int x = (int)Math.floor(Double.parseDouble(str[0].split(":")[1]));
        // // 获取截取的y坐标
        // int y = (int)Math.floor(Double.parseDouble(str[1].split(":")[1]));
        // // 获取截取的高度
        // int h = (int)Math.floor(Double.parseDouble(str[2].split(":")[1]));
        // // 获取截取的宽度
        // int w = (int)Math.floor(Double.parseDouble(str[3].split(":")[1]));
        // // 获取旋转的角度
        // int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        // try {
        // BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
        // BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
        // ByteArrayOutputStream out = new ByteArrayOutputStream();
        // boolean flag = ImageIO.write(rotateImage, prefix, out);
        // // 转换后存入数据库
        // byte[] b = out.toByteArray();
        // FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
        // } catch (Exception e) {
        // throw new Exception("图片裁剪错误！！");
        // }
        // Map<String, Object> result = new HashMap<>();
        // if (sysFileService.save(sysFile) > 0) {
        // SysUser userDO = new SysUser();
        // userDO.setUserId(userId);
        // userDO.setPicId(sysFile.getId());
        // if (userMapper.update(userDO) > 0) {
        // result.put("url", sysFile.getUrl());
        // }
        // }
        // return result;
        return null;
    }
}
