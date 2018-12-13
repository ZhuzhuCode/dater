package com.zjr.dater.business.domain;

import java.io.Serializable;

/**
 * Created by zhujr on 2018/9/29.
 * 查询基准类
 */
public class BaseOption implements Serializable {
    private static final long serialVersionUID = -8740397637655668429L;

    private int start = 0;
    private int count = 10;
    private int endrow;
    private String orderBy;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getEndrow() {
        return endrow;
    }

    public void setEndrow(int endrow) {
        this.endrow = endrow;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
