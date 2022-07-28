package com.th.vhr.bean.bo;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 * : 2022-07-12 09:39
 **/
public class RespPageBean {

    private Long total;
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
