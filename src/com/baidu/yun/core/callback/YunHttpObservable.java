package com.baidu.yun.core.callback;

import java.util.List;

import com.baidu.yun.core.event.YunHttpEvent;

public interface YunHttpObservable {

    public void addHttpCallback(YunHttpObserver callback);

    public void addBatchHttpCallBack(List<YunHttpObserver> callbacks);

    public void removeCallBack(YunHttpObserver callback);

    public void notifyAndCallback(YunHttpEvent event);

}
