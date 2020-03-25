package com.earn.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.earn.helper.entity.Tree;
import com.earn.system.entity.SysDept;
import com.earn.system.entity.SysUser;

@Service
public interface ISysUserService {
    SysUser get(Integer id);

    List<SysUser> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysUser user);

    int update(SysUser user);

    int remove(Integer userId);

    int batchremove(Integer[] userIds);

    boolean exit(Map<String, Object> params);

    Set<String> listRoles(Integer userId);

    int resetPwd(SysUser userDO) throws Exception;

    int adminResetPwd(SysUser userVO) throws Exception;

    Tree<SysDept> getTree();

    /**
     * 更新个人信息
     * 
     * @param userDO
     * @return
     */
    int updatePersonal(SysUser userDO);

    /**
     * 更新个人图片
     * 
     * @param file
     *            图片
     * @param avatar_data
     *            裁剪信息
     * @param userId
     *            用户ID
     * @throws Exception
     */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Integer userId) throws Exception;
}
