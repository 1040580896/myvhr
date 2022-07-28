package com.th.vhr.service;

import com.th.vhr.bean.Hr;
import com.th.vhr.bean.Menu;
import com.th.vhr.mapper.MenuMapper;
import com.th.vhr.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenusByHrId() {

        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    // @Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    //获取所有菜单
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    /**
     * 据角色id获取菜单id
     *
     * @param rid
     * @return
     */
    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    /**
     * 更新角色的菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        Integer result = 0;
        menuRoleMapper.deleteByRid(rid);
        if (!StringUtils.isEmpty(mids)) {

            result = menuRoleMapper.insertRecord(rid, mids);
        }
        return result == (mids==null?0:mids.length);
    }
}
