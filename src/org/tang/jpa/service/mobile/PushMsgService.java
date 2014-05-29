/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.ChatMsgDao;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.utils.DateTool;
import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

@Service
public class PushMsgService {
	@Autowired
	private ChatMsgDao chatMsgDao;
	
	public ChatMsgDao getChatMsgDao() {
		return chatMsgDao;
	}

	public void setChatMsgDao(ChatMsgDao chatMsgDao) {
		this.chatMsgDao = chatMsgDao;
	}


	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  void pushMsg(){
		
	 List<ChatMsgDTO> lists = 	chatMsgDao.selectChatMsgWaitPush();
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
    	 
    	 for(ChatMsgDTO c :lists){
             // 4. 创建请求类对象
             PushUnicastMessageRequest request = new PushUnicastMessageRequest();
             request.setDeviceType(3);
             request.setChannelId(Long.valueOf(c.getPushChannelId()));
             request.setUserId(c.getPushuserId());
             request.setMessage(c.getContent());
             // 5. 调用pushMessage接口
             PushUnicastMessageResponse response = channelClient
                     .pushUnicastMessage(request);
             c.setState("1");
             c.setSendTime(DateTool.getDateStringYMDHMS(new Date()));
             chatMsgDao.updateChatMsg(c);
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
	
}
