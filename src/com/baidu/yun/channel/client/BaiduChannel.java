package com.baidu.yun.channel.client;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.DeleteAppIoscertRequest;
import com.baidu.yun.channel.model.DeleteTagRequest;
import com.baidu.yun.channel.model.FetchMessageRequest;
import com.baidu.yun.channel.model.FetchMessageResponse;
import com.baidu.yun.channel.model.FetchTagRequest;
import com.baidu.yun.channel.model.FetchTagResponse;
import com.baidu.yun.channel.model.InitAppIoscertRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryAppIoscertRequest;
import com.baidu.yun.channel.model.QueryAppIoscertResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.QueryDeviceTypeRequest;
import com.baidu.yun.channel.model.QueryDeviceTypeResponse;
import com.baidu.yun.channel.model.QueryUserTagsRequest;
import com.baidu.yun.channel.model.QueryUserTagsResponse;
import com.baidu.yun.channel.model.SetTagRequest;
import com.baidu.yun.channel.model.UpdateAppIoscertRequest;
import com.baidu.yun.channel.model.VerifyBindRequest;

public interface BaiduChannel {

    public PushUnicastMessageResponse pushUnicastMessage(
            PushUnicastMessageRequest request) throws ChannelClientException,
            ChannelServerException;

    public PushTagMessageResponse pushTagMessage(PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public PushBroadcastMessageResponse pushBroadcastMessage(
            PushBroadcastMessageRequest request) throws ChannelClientException,
            ChannelServerException;

    public QueryBindListResponse queryBindList(QueryBindListRequest request)
            throws ChannelClientException, ChannelServerException;

    public void verifyBind(VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException;

    public FetchMessageResponse fetchMessage(FetchMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public void setTag(SetTagRequest request) throws ChannelClientException,
            ChannelServerException;

    public void deleteTag(DeleteTagRequest request)
            throws ChannelClientException, ChannelServerException;

    public FetchTagResponse fetchTag(FetchTagRequest request)
            throws ChannelClientException, ChannelServerException;

    public QueryUserTagsResponse queryUserTags(QueryUserTagsRequest request)
            throws ChannelClientException, ChannelServerException;

    public void initAppIoscert(InitAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public void updateAppIoscert(UpdateAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public void deleteAppIoscert(DeleteAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public QueryAppIoscertResponse queryAppIoscert(
            QueryAppIoscertRequest request) throws ChannelClientException,
            ChannelServerException;

    public QueryDeviceTypeResponse queryDeviceType(
            QueryDeviceTypeRequest request) throws ChannelClientException,
            ChannelServerException;

}
