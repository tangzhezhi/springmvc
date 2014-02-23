package org.tang.jpa.service.publicInformation;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.publicInformation.EmailDao;
import org.tang.jpa.dto.publicInformation.EmailDTO;

@Service
public class MailService {
	
	@Autowired
	private EmailDao emailDao;
    //简单的文本邮件发送类
	@Autowired
    private MailSender mailSender;
    //复杂邮件发送类
	@Autowired
    private JavaMailSender javaMailSender;
    
//    public MailSender getMailSender() {
//        return mailSender;
//    }
//
//    public void setMailSender(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//    
//    public JavaMailSender getJavaMailSender() {
//        return javaMailSender;
//    }
//
//    public void setJavaMailSender(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//    
//    
//    public EmailDao getEmailDao() {
//		return emailDao;
//	}
//
//	public void setEmailDao(EmailDao emailDao) {
//		this.emailDao = emailDao;
//	}
	
	/**
     * 发送简单的文本邮件
     * @param email
     */
    public int sendAccountInfo(EmailDTO dto){
    	int flag = 0;
    	SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(dto.getFromadd()==null?"tangzhezi@126.com":dto.getFromadd());
        smm.setSubject(dto.getSubject());
        smm.setTo(dto.getToadd());
        smm.setText(dto.getContent());
        try {
			mailSender.send(smm);
		} catch (MailException e) {
			flag = 2;
			e.printStackTrace();
		}
        flag = 1;
    	return flag;
    }
	
	
	
	
	/**
     * 发送简单的文本邮件
     * @param email
     */
    public int send(EmailDTO dto){
    	int flag = 0;
    	List<EmailDTO> list = emailDao.selectEmailAllUnSend(dto);
    	for(EmailDTO d : list){
        	SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(d.getFromadd());
            smm.setSubject(d.getSubject());
            if(d.getToadd().contains(";")){
            	String[] to = d.getToadd().split(";");
            	 smm.setTo(to);
            }
            smm.setText(d.getContent());
            mailSender.send(smm);
            d.setStatus("1");
            emailDao.updateEmail(d);
            flag = 1;
    	}
    	return flag;
    }
    
    
    
    public int sendMimeAccoutInfo(EmailDTO emailDTO) throws MessagingException{
    	int flag = 0 ;
        MimeMessage mm = javaMailSender.createMimeMessage();
        //加上编码，解决中文乱码
        MimeMessageHelper helper = new MimeMessageHelper(mm,true,"UTF-8");
        helper.setFrom(emailDTO.getFromadd()==null ? "tangzhezi@126.com":emailDTO.getFromadd());
        helper.setTo(emailDTO.getToadd());
        helper.setSubject(emailDTO.getSubject()==null ? "您的账号与密码":emailDTO.getSubject());
        helper.setText("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\"></head><body><h1><a href='http://www.examtom.com'>"+emailDTO.getContent()+"</a></h1></body></html>",true);
        javaMailSender.send(mm);
        flag = 1;
    	return flag;
    }
    
    
    
    /**
     * 发送复杂邮件
     * @param EmailDTO
     * @throws MessagingException
     */
    public int sendMime(EmailDTO emailDTO) throws MessagingException{
    	int flag = 0;
    	List<EmailDTO> list = emailDao.selectEmailAllUnSend(emailDTO);
    	for(EmailDTO d : list){
    		   MimeMessage mm = javaMailSender.createMimeMessage();
    	        //加上编码，解决中文乱码
    	        MimeMessageHelper helper = new MimeMessageHelper(mm,true,"UTF-8");
    	        
    	        helper.setFrom(d.getFromadd());
    	        
    	        if(d.getToadd().contains(";")){
    	        	String[] to = d.getToadd().split(";");
    	        	helper.setTo(to);
    	        }
    	        
    	        helper.setSubject(d.getSubject());
    	        helper.setText(d.getContent(),true);
    	        
    	        if(d.getAttchfileurl()!=null && d.getAttchfileurl().contains(";")) {
    	        	
    	        	String[] attachFiles = d.getAttchfileurl().split(";");
    	        	 List<AbstractResource> resources = new ArrayList<AbstractResource>();
    	        	for(String aUrl : attachFiles){
    	        		 FileSystemResource file = new FileSystemResource(aUrl);
    	        		 resources.add(file);
    	        	}
    	        	
    	        	d.setResources(resources);
    	        	
    	            //添加附件
    	            if(d.getResources()!=null && d.getResources().size()>0) {
    	                for(AbstractResource resource:d.getResources()) {
    	                    helper.addAttachment(resource.getFilename(), resource);
    	                }
    	            }
    	        }
    	        javaMailSender.send(mm);
    	        d.setStatus("1");
                emailDao.updateEmail(d);
                flag = 1;
    		}
    	return flag;
    }
    

}