package com.baidu.channel.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.BindInfo;
import com.baidu.yun.channel.model.ChannelMessage;
import com.baidu.yun.channel.model.FetchMessageRequest;
import com.baidu.yun.channel.model.FetchMessageResponse;
import com.baidu.yun.channel.model.InitAppIoscertRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.VerifyBindRequest;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class ChannelClientTest {


    @Test
    public void testQueryBindList() {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "9HCWGt0jqjxwgTaynTvOGaaU";
        String secretKey = "WPqtDFZLiGQcQf5xZnFL603o0mf0KjZd";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. 创建请求类对象   userId=1000414150712059691 channelId=4531399570486995899 requestId=2278587875
            QueryBindListRequest request = new QueryBindListRequest();
            request.setUserId("1000414150712059691");

            // 5. 调用queryBindList接口
            QueryBindListResponse response = channelClient
                    .queryBindList(request);

            // 6. 对返回的结果对象进行操作
            List<BindInfo> bindInfos = response.getBinds();
            for (BindInfo bindInfo : bindInfos) {
                long channelId = bindInfo.getChannelId();
                String userId = bindInfo.getUserId();
                int status = bindInfo.getBindStatus();
                System.out.println("channel_id:" + channelId + ", user_id: "
                        + userId + ", status: " + status);

                String bindName = bindInfo.getBindName();
                long bindTime = bindInfo.getBindTime();
                String deviceId = bindInfo.getDeviceId();
                int deviceType = bindInfo.getDeviceType();
                long timestamp = bindInfo.getOnlineTimestamp();
                long expire = bindInfo.getOnlineExpires();

                System.out.println("bind_name:" + bindName + "\t"
                        + "bind_time:" + bindTime);
                System.out.println("device_type:" + deviceType + "\tdeviceId"
                        + deviceId);
                System.out.println(String.format("timestamp: %d, expire: %d",
                        timestamp, expire));

            }

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushUnicastMessage() {

        /*
         * @brief 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容)
         * message_type = 0 (默认为0)
         */
        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "9HCWGt0jqjxwgTaynTvOGaaU";
        String secretKey = "WPqtDFZLiGQcQf5xZnFL603o0mf0KjZd";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3);
            request.setChannelId(4035698885061886729L);
            request.setUserId("1144280722819924199");

            request.setMessage("hello channel");

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            Assert.assertEquals(1, response.getSuccessAmount());

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushUnicastAndroidNotification() {

        /*
         * @brief 向Android端设备推送单播消息
         * message_type = 1
         * device_type = 3
         */

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "EURAf2Qzru12h1m57nYgFImj";
        String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3);
            request.setChannelId(4035698885061886729L);
            request.setUserId("814432199857187466");

            request.setMessageType(1);
            request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            Assert.assertEquals(1, response.getSuccessAmount());

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushUnicastIosNotification() {

        /*
         * @brief 推送单播消息(IOS APNS)
         * message_type = 1 
         * device_type = 4
         */

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "EURAf2Qzru12h1m57nYgFImj";
        String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(4);
            // request.setChannelId(4035698885061886729L);
            request.setUserId("814432199857187466");

            request.setMessageType(1);
            request.setMessage("{aps}");

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            Assert.assertEquals(1, response.getSuccessAmount());

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushTagMessage() {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "9HCWGt0jqjxwgTaynTvOGaaU";
        String secretKey = "WPqtDFZLiGQcQf5xZnFL603o0mf0KjZd";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            PushTagMessageRequest request = new PushTagMessageRequest();
            request.setMessageType(0);
            request.setDeviceType(3);
            request.setTagName("xxxx");
            request.setMessage("{\"title\":\"标题\",\"description\":\"推荐内容xxx\"}");

            // 5. 调用pushMessage接口
            PushTagMessageResponse response = channelClient
                    .pushTagMessage(request);
            if (response.getSuccessAmount() == 1) {
                // TODO
            }

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushBroadcastMessage() {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "9HCWGt0jqjxwgTaynTvOGaaU";
        String secretKey = "WPqtDFZLiGQcQf5xZnFL603o0mf0KjZd";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setMessageType(0);
            request.setDeviceType(3);
            request.setMessage("{\"title\":\"标题\",\"description\":\"推荐内容\"}");

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);
            if (response.getSuccessAmount() == 1) {
                // TODO
            }

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
    }

    @Test
    public void testBindVerify() {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "9HCWGt0jqjxwgTaynTvOGaaU";
        String secretKey = "WPqtDFZLiGQcQf5xZnFL603o0mf0KjZd";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            VerifyBindRequest request = new VerifyBindRequest();
            request.setChannelId(4035698885061886729L);
            request.setUserId("1144280722819924199");

            // 5. 调用 verifyBind 接口
            channelClient.verifyBind(request);

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    // ----------------------------------------------------------------------------
    @Test
    public void testFetchMessage() {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "9HCWGt0jqjxwgTaynTvOGaaU";
        String secretKey = "WPqtDFZLiGQcQf5xZnFL603o0mf0KjZd";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            FetchMessageRequest request = new FetchMessageRequest();
            request.setUserId("1144280722819924199");

            // 5. 调用 fetchMessage 接口
            FetchMessageResponse resp = channelClient.fetchMessage(request);
            List<ChannelMessage> messages = resp.getMessages();
            for (ChannelMessage msg : messages) {
                System.out.println(msg.getData());
            }

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testInitIosCert() {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = "EURAf2Qzru12h1m57nYgFImj";
        String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. 创建请求类对象
            InitAppIoscertRequest request = new InitAppIoscertRequest();
            request.setName("name");
            request.setDescription("description");

            request.setDevCert("");
            request.setReleaseCert("");

            // 5. 调用 initAppIoscert 接口
            channelClient.initAppIoscert(request);

        } catch (ChannelClientException e) {
            e.printStackTrace();
            // 处理客户端错误异常
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

}
