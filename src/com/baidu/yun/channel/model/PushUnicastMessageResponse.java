package com.baidu.yun.channel.model;

import com.baidu.yun.core.annotation.JSonPath;

public class PushUnicastMessageResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\success_amount")
    private int successAmount = 0;

    public int getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(int successAmount) {
        this.successAmount = successAmount;
    }

}
