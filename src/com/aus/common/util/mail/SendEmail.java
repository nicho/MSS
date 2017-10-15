package com.aus.common.util.mail;

import javax.mail.MessagingException;

public class SendEmail {
	
	private static MailSenderInfo mailInfo = new MailSenderInfo();
	
	public static void sendEmail(String title,String content,String address) throws MessagingException {
		// 这个类主要是设置邮件
		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("it@Ausnutria.com");
		mailInfo.setPassword("au123456789");// 您的邮箱密码
		mailInfo.setFromAddress("it@Ausnutria.com");
		
//		mailInfo.setUserName("800@Ausnutria.com");
//		mailInfo.setPassword("au8786122");// 您的邮箱密码
//		mailInfo.setFromAddress("800@Ausnutria.com");
		mailInfo.setToAddress(address);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);
	}
}
