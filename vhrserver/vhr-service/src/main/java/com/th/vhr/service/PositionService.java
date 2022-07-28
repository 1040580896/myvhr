package com.th.vhr.service;

import com.th.vhr.bean.Position;
import com.th.vhr.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public  List<Position> getAllPositions() {
       return positionMapper.getAllPositions();
    }

    // 添加职位信息
    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    // 更新职位信息
    public Integer updatePositions(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    // 删除职位信息
    public int deletePosition(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    // 批量删除
    public Integer deletePositionsByIds(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }
}
