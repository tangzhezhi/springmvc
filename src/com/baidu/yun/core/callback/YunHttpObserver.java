package com.baidu.yun.core.callback;

import com.baidu.yun.core.event.YunHttpEvent;

public interface YunHttpObserver {

    public void onHandle(YunHttpEvent event);

}
