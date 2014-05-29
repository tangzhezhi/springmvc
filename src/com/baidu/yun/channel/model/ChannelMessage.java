package com.baidu.yun.channel.model;

import com.baidu.yun.core.annotation.JSonPath;

public class ChannelMessage {

    @JSonPath(path = "channel_id")
    private long channelId;

    @JSonPath(path = "msg_id")
    private String msgId;

    @JSonPath(path = "msg_key")
    private String msgKey;

    @JSonPath(path = "data")
    private String data;

    @JSonPath(path = "length")
    private int length;

    @JSonPath(path = "type")
    private int type;

    @JSonPath(path = "expired")
    private long expired;

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

}
