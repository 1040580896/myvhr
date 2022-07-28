package com.th.vhr.controller.system.basic;

import com.th.vhr.bean.Position;
import com.th.vhr.bean.RespBean;
import com.th.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    // 查询所以职位信息
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    // 添加职位信息
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position)==1){
            return RespBean.ok("添加成功");
        }
     return  RespBean.error("添加失败");
    }

    // 更新职位信息
    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position){
        if(positionService.updatePositions(position)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    // 删除职位信息
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        // todo 判断该角色是否还有人员
        if(positionService.deletePosition(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    // 批量删除
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids){
        if (positionService.deletePositionsByIds(ids)==1) {
            return RespBean.ok("批量删除成功");
        }
        return RespBean.error("删除失败");
    }
}
