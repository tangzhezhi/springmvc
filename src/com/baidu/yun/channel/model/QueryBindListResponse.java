package com.baidu.yun.channel.model;

import java.util.LinkedList;
import java.util.List;

import com.baidu.yun.core.annotation.JSonPath;

public class QueryBindListResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\total_num")
    private int totalNum;

    @JSonPath(path = "response_params\\binds")
    private List<BindInfo> binds = new LinkedList<BindInfo>();

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<BindInfo> getBinds() {
        return binds;
    }

    public void setBinds(List<BindInfo> binds) {
        this.binds = binds;
    }

}
