package com.baidu.yun.channel.model;

import com.baidu.yun.core.annotation.JSonPath;

public class BindInfo {

    @JSonPath(path = "channel_id")
    private long channelId;

    @JSonPath(path = "user_id")
    private String userId;

    @JSonPath(path = "device_id")
    private String deviceId;

    @JSonPath(path = "device_type")
    private int deviceType;

    @JSonPath(path = "bind_name")
    private String bindName;

    @JSonPath(path = "bind_time")
    private long bindTime;

    @JSonPath(path = "info")
    private String info;

    @JSonPath(path = "bind_status")
    private int bindStatus;

    @JSonPath(path = "online_status")
    private String onlineStatus;

    @JSonPath(path = "online_timestamp")
    private long onlineTimestamp;

    @JSonPath(path = "online_expires")
    private long onlineExpires;

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getBindName() {
        return bindName;
    }

    public void setBindName(String bindName) {
        this.bindName = bindName;
    }

    public long getBindTime() {
        return bindTime;
    }

    public void setBindTime(long bindTime) {
        this.bindTime = bindTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(int bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public long getOnlineTimestamp() {
        return onlineTimestamp;
    }

    public void setOnlineTimestamp(long onlineTimestamp) {
        this.onlineTimestamp = onlineTimestamp;
    }

    public long getOnlineExpires() {
        return onlineExpires;
    }

    public void setOnlineExpires(long onlineExpires) {
        this.onlineExpires = onlineExpires;
    }

}
