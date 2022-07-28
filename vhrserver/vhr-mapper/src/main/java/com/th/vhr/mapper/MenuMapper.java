package com.th.vhr.mapper;

import com.th.vhr.bean.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 获取菜单
     * @param id
     * @return
     */
    List<Menu> getMenusByHrId(Integer id);

    List<Menu> getAllMenusWithRole();

    // 获取所有菜单
    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);
}