package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;

public class PushTagMessageRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.PUSH_TYPE, param = R.REQUIRE)
    private final Integer pushType = 2;

    @HttpParamKeyName(name = BaiduChannelConstants.DEVICE_TYPE, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 5)
    private Integer deviceType = null;

    @HttpParamKeyName(name = BaiduChannelConstants.DEPLOY_STATUS, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 2)
    private Integer deployStatus = null;

    @HttpParamKeyName(name = BaiduChannelConstants.MESSAGE_TYPE, param = R.OPTIONAL)
    private Integer messageType = new Integer(0);

    @HttpParamKeyName(name = BaiduChannelConstants.MESSAGES, param = R.REQUIRE)
    private String message = null;

    @HttpParamKeyName(name = BaiduChannelConstants.MSG_KEYS, param = R.REQUIRE)
    private String msgKey = "channel_msg_key";

    @HttpParamKeyName(name = BaiduChannelConstants.TAG_NAME, param = R.REQUIRE)
    private String tagName = null;

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getTagName() {
        return tagName;
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

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getPushType() {
        return pushType;
    }

    public Integer getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(Integer deployStatus) {
        this.deployStatus = deployStatus;
    }

}
