package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.HttpPathKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;

public class VerifyBindRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.REQUIRE)
    private String userId = null;

    @HttpPathKeyName(param = R.REQUIRE)
    private Long channelId = null;

    @HttpParamKeyName(name = BaiduChannelConstants.DEVICE_TYPE, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 5)
    private Integer deviceType = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

}
