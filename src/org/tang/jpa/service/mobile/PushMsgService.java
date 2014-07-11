/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.tang.jpa.dao.mobile.ChatMsgDao;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.dto.system.NoticeDTO;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MobileConstant;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.google.gson.Gson;

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
	 * 
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


    	 Gson gson = new Gson();
    	 for(ChatMsgDTO c :lists){
    	     try {
    	    	 // 4. 创建请求类对象
	             PushUnicastMessageRequest request = new PushUnicastMessageRequest();
	             request.setDeviceType(3);
	             request.setChannelId(Long.valueOf(c.getPushChannelId()));
	             request.setUserId(c.getPushuserId());
	             String jsonString = gson.toJson(c);
	             System.out.println("jsonString...."+jsonString);
	             request.setMessage(jsonString);
	             
	             // 5. 调用pushMessage接口
	             PushUnicastMessageResponse response = channelClient
	                     .pushUnicastMessage(request);
	             c.setState("1");
	             c.setSendTime(DateTool.getDateStringYMDHMS(new Date()));
	             chatMsgDao.updateChatMsg(c);
    	     }
    	     catch (ChannelClientException e) {
    	         // 处理客户端错误异常
    	         e.printStackTrace();
    	     } catch (ChannelServerException e) {
    	         // 处理服务端错误异常
    	         System.out.println(String.format(
    	                 "request_id: %d, error_code: %d, error_message: %s",
    	                 e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
    	         
    	         c.setState("2");
    	         chatMsgDao.updateChatMsg(c);
    	         continue;
    	     }     
    	 }
	}
	
	
	
	
	/**
	 * 实时发送
	 * @param udto
	 * @return
	 */
	public  int pushMsgRealTime(ChatMsgDTO entity){
		int flag = 0;
      /*
      * @brief 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容)
      * message_type = 0 (默认为0)
      */
     // 1. 设置developer平台的ApiKey/SecretKey
     String apiKey = MobileConstant.baidu_push_apiKey;
     String secretKey = MobileConstant.baidu_push_secretKey;
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
     	

    	 	Gson gson = new Gson();
    	     try {
    	    	 // 4. 创建请求类对象
	             PushUnicastMessageRequest request = new PushUnicastMessageRequest();
	             request.setDeviceType(3);
	             request.setChannelId(Long.valueOf(entity.getPushChannelId()));
	             request.setUserId(entity.getPushuserId());
		            
		         ModelMap mm = new ModelMap();
			     mm.put("sessionKey", "examTang");
			     mm.put("msgFlag", MobileConstant.chat_msg);
			     mm.put("response", entity);
	             
	             String jsonString = gson.toJson(mm);
	             System.out.println("jsonString...."+jsonString);
	             request.setMessage(jsonString);
	             // 5. 调用pushMessage接口
	             PushUnicastMessageResponse response = channelClient
	                     .pushUnicastMessage(request);
	             
	             
	             System.out.println(response.getSuccessAmount());
	             
	             entity.setState("1");
	             entity.setSendTime(DateTool.getDateStringYMDHMS(new Date()));
	             flag = chatMsgDao.updateChatMsg(entity);
    	     }
    	     catch (ChannelClientException e) {
    	         // 处理客户端错误异常
    	         e.printStackTrace();
    	     } catch (ChannelServerException e) {
    	         // 处理服务端错误异常
    	         System.out.println(String.format(
    	                 "request_id: %d, error_code: %d, error_message: %s",
    	                 e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
    	         
    	         entity.setState("2");
    	         flag = chatMsgDao.updateChatMsg(entity);
    	     }     
    	  return flag;
	}
	
	/**
	 * 广播公告消息
	 */
	public int pushBroadcastMessage(NoticeDTO dto) {
		int flag = 0;
	        // 1. 设置developer平台的ApiKey/SecretKey
	     String apiKey = MobileConstant.baidu_push_apiKey;
	     String secretKey = MobileConstant.baidu_push_secretKey;
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
	            Gson gson = new Gson();
	            
	            ModelMap mm = new ModelMap();
		        mm.put("sessionKey", "examTang");
		    	mm.put("msgFlag", MobileConstant.notice_msg);
		    	mm.put("response", dto);
	            request.setMessage( gson.toJson(mm));
	            
	            // 5. 调用pushMessage接口
	            PushBroadcastMessageResponse response = channelClient
	                    .pushBroadcastMessage(request);
	            if (response.getSuccessAmount() == 1) {
System.out.println("response:::::::::::::::::"+response.getSuccessAmount());
	            	flag = 1;
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
	        
	        return flag;
	    }
	
	
	
	
	
	
	
}
