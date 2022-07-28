package com.th.vhr.service;

import com.th.vhr.bean.Role;
import com.th.vhr.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    // 获取所以角色
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    // 添加角色
    public int addRole(Role role) {

        if (!role.getName().startsWith("ROLE_")) {

            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    /**
     * 删除角色
     * @param rid
     * @return
     */
    public int deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
