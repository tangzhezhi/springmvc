package org.tang.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tang.jpa.dto.publicInformation.EmailDTO;
import org.tang.jpa.service.publicInformation.MailService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:applicationContext-mails.xml"})
public class MailTester {
    
    @Autowired
    private MailService mailService;
    
    @Test
    public void testSendMail() {
        EmailDTO email = new EmailDTO();
        email.setFromadd("tangzhezi@126.com");
        email.setToadd("330882908@qq.com;");
        email.setSubject("简单文本邮件");
        email.setContent("how are you.i am from china!\r你好,程序猿！！");
        mailService.send(email);
    }
    
    @Test
    public void testSendMimeMail() throws MessagingException {
    	EmailDTO email = new EmailDTO();
    	email.setFromadd("tangzhezi@126.com");
        email.setToadd("330882908@qq.com;tangzhezi2013@gmail.com");
        email.setSubject("复杂邮件");
        String text = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\"></head><body><h1><a href='http://luxh.cnblogs.com'>我的博客</a></h1></body></html>";
        email.setContent(text);
        
        
        List<AbstractResource> resources = new ArrayList<AbstractResource>();
        //添加附件
        ClassPathResource file1 = new ClassPathResource("shengtong.ico");
        FileSystemResource file2 = new FileSystemResource("d:/111.txt");
        resources.add(file1);
        resources.add(file2);
        email.setResources(resources);
        mailService.sendMime(email);
    }
}
