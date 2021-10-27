package com.mcs.meinian.entity;

import com.mcs.meinian.pojo.TravelItem;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 */
public class PageResult implements Serializable{
    private Long total;//总记录数
    private List<TravelItem> rows;//当前页结果
    public PageResult(Long total, List<TravelItem> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<TravelItem> getRows() {
        return rows;
    }
    public void setRows(List<TravelItem> rows) {
        this.rows = rows;
    }
}
