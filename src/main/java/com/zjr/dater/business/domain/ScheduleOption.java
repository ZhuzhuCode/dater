package com.zjr.dater.business.domain;

/**
 * Created by zhujr on 2018/10/12.
 */
public class ScheduleOption extends BaseOption {
    private static final long serialVersionUID = 1247053525983490279L;

    private long userId;
    private String content;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
