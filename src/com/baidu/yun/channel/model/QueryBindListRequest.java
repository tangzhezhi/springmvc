package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;

public class QueryBindListRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.REQUIRE)
    private String userId = null;

    @HttpParamKeyName(name = BaiduChannelConstants.DEVICE_TYPE, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 5)
    private Integer deviceType = null;

    @HttpParamKeyName(name = BaiduChannelConstants.START, param = R.OPTIONAL)
    private Integer start = new Integer(0);

    @HttpParamKeyName(name = BaiduChannelConstants.LIMIT, param = R.OPTIONAL)
    private Integer limit = new Integer(10);

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
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
