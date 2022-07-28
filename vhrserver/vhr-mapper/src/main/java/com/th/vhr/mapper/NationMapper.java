package com.th.vhr.mapper;

import com.th.vhr.bean.Nation;

import java.util.List;

public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);

    /**
     * 查询所有民族
     * @return
     */
    List<Nation> getAllNations();
}