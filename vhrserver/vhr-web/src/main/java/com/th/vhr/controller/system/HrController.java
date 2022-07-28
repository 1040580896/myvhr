package com.th.vhr.controller.system;

import com.th.vhr.bean.Hr;
import com.th.vhr.bean.RespBean;
import com.th.vhr.bean.Role;
import com.th.vhr.service.HrService;
import com.th.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: vhr
 * @description:
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

    /**
     * 获取所有的hr
     * @return
     */
    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords){

        return hrService.getAllHrs(keywords);
    }

    /**
     * 更新hr信息
     * @param hr
     * @return
     */
    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if(hrService.updateHr(hr)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    /**
     * 更新hr的角色
     * @param hrid
     * @param rids
     * @return
     */
    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid,Integer[] rids){
        if(hrService.updateHrRole(hrid,rids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 删除hr
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id){
        if(hrService.deleteHrById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
