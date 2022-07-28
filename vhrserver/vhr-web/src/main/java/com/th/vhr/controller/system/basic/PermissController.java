package com.th.vhr.controller.system.basic;

import com.th.vhr.bean.Menu;
import com.th.vhr.bean.RespBean;
import com.th.vhr.bean.Role;
import com.th.vhr.service.MenuService;
import com.th.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    /**
     * 获取所以角色
     *
     * @return
     */
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 获取所有菜单
     * @return
     */
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    /**
     * 根据角色id获取菜单id
     * @param rid
     * @return
     */
    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable("rid") Integer rid){

        return menuService.getMidsByRid(rid);
    }

    /**
     * 更新角色的菜单
     * @param rid
     * @param mids
     * @return
     */
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        if(menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role)==1){
            return RespBean.ok("添加成功");
        }

        return RespBean.error("添加失败");
    }

    /**
     * 删除角色
     * @param rid
     * @return
     */
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if(roleService.deleteRoleById(rid)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
