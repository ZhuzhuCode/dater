package com.zjr.dater.business.model;

import com.zjr.dater.common.utils.DateUtils;

import java.util.Date;
public class User {

    private Long id;

    private String name;

    private String password;

    private String email;

    private int status;//1有效账户 0禁止登录

    private Date createtime;

    private Date operatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    /**************自定义方法****************/
    public String getCreatetimeCN() {
        return DateUtils.dateToStrLong(createtime);
    }

    public String getOperatetimeCN() {
        return DateUtils.dateToStrLong(operatetime);
    }
}