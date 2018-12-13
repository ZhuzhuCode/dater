package com.zjr.dater.business.model;

import com.zjr.dater.common.utils.DateUtils;

import java.util.Date;

public class Schedule {
    private Long id;

    private Long userid;

    private Date createtime;

    private String content;

    private Date contentstart;

    private Date contentend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getContentstart() {
        return contentstart;
    }

    public void setContentstart(Date contentstart) {
        this.contentstart = contentstart;
    }

    public Date getContentend() {
        return contentend;
    }

    public void setContentend(Date contentend) {
        this.contentend = contentend;
    }

    /****************自定义方法******************/
    public String getCreatetimeCN() {
        return DateUtils.dateToStrLong(createtime);
    }
    public String getContentstartCN() {
        return DateUtils.dateToStrLong(contentstart);
    }
    public String getContentendCN() {
        return DateUtils.dateToStrLong(contentend);
    }
}