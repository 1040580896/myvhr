package com.th.vhr.mapper;

import com.th.vhr.bean.Politicsstatus;

import java.util.List;

public interface PoliticsstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    /**
     * 获取所有政治面貌
     * @return
     */
    List<Politicsstatus> getAllPoliticsstatus();
}