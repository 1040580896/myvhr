package com.th.vhr.service;

import com.th.vhr.bean.Nation;
import com.th.vhr.mapper.NationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@Service
public class NationService {

    @Autowired
    NationMapper nationMapper;

    /**
     * 查询所有民族
     * @return
     */
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
