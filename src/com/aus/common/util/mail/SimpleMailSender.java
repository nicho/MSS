package com.aus.common.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.aus.common.util.MD5Util;

public class SimpleMailSender {
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo)throws MessagingException  {
		return commonSendEmail(mailInfo);
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 * @throws MessagingException 
	 */
	public boolean sendHtmlMail(MailSenderInfo mailInfo) throws MessagingException {
		return commonSendEmail(mailInfo);
	}
	
	

	public boolean commonSendEmail(MailSenderInfo mailInfo) throws MessagingException{
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(pro, authenticator); 
		//Session.getInstance(properties, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			if("notice@ausnutria.com".equals(mailInfo.getFromAddress())){
				//设置自定义发件人昵称
				String nick="";
				try {    
		            nick=javax.mail.internet.MimeUtility.encodeText("澳优乳业");    
		        } catch (UnsupportedEncodingException e) {    
		            e.printStackTrace();    
		        }  
				mailMessage.setFrom(new InternetAddress(nick+" <"+mailInfo.getFromAddress()+">"));
			}
			
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			MimeBodyPart html = new MimeBodyPart();//创建一个包含HTML内容的
			//MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象   
			
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			throw new MessagingException();
		}
	}
	public static void main(String[] args) {
		SimpleMailSender xx=new SimpleMailSender();
		 MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("notice@ausnutria.com");
		mailInfo.setPassword(MD5Util.decryptMD5("YXDCo7BeNapQHimwVdtLbg=="));// 您的邮箱密码
		mailInfo.setFromAddress("notice@ausnutria.com");
 
		mailInfo.setToAddress("972111205@qq.com");
		mailInfo.setSubject("测试");
		mailInfo.setContent("测试");
		try {
			xx.sendHtmlMail(mailInfo);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
