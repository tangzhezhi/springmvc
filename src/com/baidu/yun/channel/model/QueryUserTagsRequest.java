package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;

public class QueryUserTagsRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.REQUIRE)
    private String userId = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
