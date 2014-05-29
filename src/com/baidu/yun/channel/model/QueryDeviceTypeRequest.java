package com.baidu.yun.channel.model;

import com.baidu.yun.core.annotation.HttpPathKeyName;
import com.baidu.yun.core.annotation.R;

public class QueryDeviceTypeRequest extends ChannelRequest {

    @HttpPathKeyName(param = R.OPTIONAL)
    private Long channelId = null;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

}
