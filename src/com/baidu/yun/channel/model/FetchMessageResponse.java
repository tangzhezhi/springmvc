package com.baidu.yun.channel.model;

import java.util.LinkedList;
import java.util.List;

import com.baidu.yun.core.annotation.JSonPath;

public class FetchMessageResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\total_num")
    private int totalNum = 0;

    @JSonPath(path = "response_params\\messages")
    private List<ChannelMessage> messages = new LinkedList<ChannelMessage>();

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ChannelMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChannelMessage> messages) {
        this.messages = messages;
    }

}
