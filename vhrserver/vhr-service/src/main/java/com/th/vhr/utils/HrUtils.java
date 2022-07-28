package com.th.vhr.utils;

import com.th.vhr.bean.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
public class HrUtils {
    
    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
