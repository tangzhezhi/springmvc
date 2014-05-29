package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;

public class FetchTagRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.TAG_NAME, param = R.OPTIONAL)
    private String name;

    @HttpParamKeyName(name = BaiduChannelConstants.START, param = R.OPTIONAL)
    private Integer start = new Integer(0);

    @HttpParamKeyName(name = BaiduChannelConstants.LIMIT, param = R.OPTIONAL)
    private Integer limit = new Integer(10);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
