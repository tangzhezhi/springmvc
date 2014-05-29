package com.baidu.yun.channel.model;

import com.baidu.yun.channel.constants.BaiduChannelConstants;
import com.baidu.yun.core.annotation.HttpParamKeyName;
import com.baidu.yun.core.annotation.R;
import com.baidu.yun.core.annotation.RangeRestrict;

public class DeleteTagRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.TAG_NAME, param = R.REQUIRE)
    @RangeRestrict(minLength = 1, maxLength = 128)
    private String tag;

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 256)
    private String userId;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
