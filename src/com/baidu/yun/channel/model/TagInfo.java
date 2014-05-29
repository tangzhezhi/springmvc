package com.baidu.yun.channel.model;

import com.baidu.yun.core.annotation.JSonPath;

public class TagInfo {

    @JSonPath(path = "tid")
    private int tagId;

    @JSonPath(path = "name")
    private String name;

    @JSonPath(path = "info")
    private String info;

    @JSonPath(path = "type")
    private int type;

    @JSonPath(path = "create_time")
    private long createTime;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

}
