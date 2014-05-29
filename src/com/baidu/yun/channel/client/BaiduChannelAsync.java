package com.baidu.yun.channel.client;

import java.util.concurrent.Future;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.QueryUserTagsRequest;
import com.baidu.yun.channel.model.QueryUserTagsResponse;
import com.baidu.yun.channel.model.VerifyBindRequest;

public interface BaiduChannelAsync {

    public Future<PushUnicastMessageResponse> pushUnicastMessageAsync(
            final PushUnicastMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<Void> pushTagMessageAsync(final PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<Void> pushBroadcastMessageAsync(
            final PushBroadcastMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<QueryBindListResponse> queryBindListAsync(
            final QueryBindListRequest request) throws ChannelClientException,
            ChannelServerException;

    public Future<Void> verifyBindAsync(final VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<QueryUserTagsResponse> queryUserTagsAsync(
            final QueryUserTagsRequest request) throws ChannelClientException,
            ChannelServerException;

}
