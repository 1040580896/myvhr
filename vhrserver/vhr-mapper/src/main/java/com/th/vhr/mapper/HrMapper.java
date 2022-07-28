package com.th.vhr.mapper;

import com.th.vhr.bean.Hr;
import com.th.vhr.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);


    Hr loadUserByUsername(String username);

    /**
     * 根据用户id获取角色
     *
     * @param id
     * @return
     */
    List<Role> getHrRolesById(Integer id);

    /**
     * 获取所有的hr 除了自己
     *
     * @param id
     * @param keywords
     * @return
     */
    List<Hr> getAllHrs(@Param("id") Integer id, @Param("keywords") String keywords);

    List<Hr> getAllHrsExceptCurrentHr(Integer id);

}