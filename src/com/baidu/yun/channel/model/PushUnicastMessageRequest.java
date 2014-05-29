package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;

public class PushUnicastMessageRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.PUSH_TYPE, param = R.REQUIRE)
    private final Integer pushType = 1;

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.REQUIRE)
    private String userId = null;

    @HttpParamKeyName(name = BaiduChannelConstants.CHANNEL_ID, param = R.OPTIONAL)
    private Long channelId = null;

    @HttpParamKeyName(name = BaiduChannelConstants.DEVICE_TYPE, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 5)
    private Integer deviceType = null;

    @HttpParamKeyName(name = BaiduChannelConstants.DEPLOY_STATUS, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 2)
    private Integer deployStatus = null;

    @HttpParamKeyName(name = BaiduChannelConstants.MESSAGE_TYPE, param = R.OPTIONAL)
    @RangeRestrict(minLength = 0, maxLength = 1)
    private Integer messageType = new Integer(0);

    @HttpParamKeyName(name = BaiduChannelConstants.MESSAGES, param = R.REQUIRE)
    private String message = null;

    @HttpParamKeyName(name = BaiduChannelConstants.MSG_KEYS, param = R.REQUIRE)
    private String msgKey = "channel_msg_key";

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

    public Integer getPushType() {
        return pushType;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public Integer getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(Integer deployStatus) {
        this.deployStatus = deployStatus;
    }

}
