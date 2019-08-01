package service.impl;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import common.frame.helper.AuthenticatorGenerator;
import common.frame.helper.HostType;

import service.HelperService;

public class HelperServiceImpl implements HelperService{
	
	private String sendMailUsername;
	private String sendMailPassword;
	private String fromMailAddr;
	private String toMailAddr;

	@Override
	public void sendMail(String messageSubject, String attachPath) {
		// TODO Auto-generated method stub		  
		  
		  Session session = Session.getDefaultInstance(HostType.NETEASE.getProperties(),AuthenticatorGenerator.getAuthenticator(sendMailUsername, sendMailPassword));
		  
		  MimeMessage message = new MimeMessage(session);
		  
		  try {
			  
			  String[] mailList = toMailAddr.split(";");
			  
			  for (int i = 0; i < mailList.length; i++) {
				  
				  message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailList[i]));//收件人地址
				
			}
		
			message.setFrom(new InternetAddress(fromMailAddr));//发件地址	
			message.setSubject(messageSubject);//邮件主题
			
			//设置邮件正文内容
			BodyPart messagePart = new MimeBodyPart();
			messagePart.setText("AutomationTest results are refered to the attachment!");

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(messagePart);
			
			//设置邮件附件
			messagePart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(attachPath);
			messagePart.setDataHandler(new DataHandler(fds));
			messagePart.setFileName(fds.getName());
			mp.addBodyPart(messagePart);

			message.setContent(mp);
			
			//发送邮件
			Transport.send(message);
			
			System.out.println("Mail was sent to "+toMailAddr.toString());
			
			
		} catch (AddressException ex) {
			// TODO Auto-generated catch block
			System.err.println("AddressException..." + ex);
			ex.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.err.println("Cannot send email. " + e);
			e.printStackTrace();
		}
		
	}

	public String getSendMailUsername() {
		return sendMailUsername;
	}

	public void setSendMailUsername(String sendMailUsername) {
		this.sendMailUsername = sendMailUsername;
	}

	public String getSendMailPassword() {
		return sendMailPassword;
	}

	public void setSendMailPassword(String sendMailPassword) {
		this.sendMailPassword = sendMailPassword;
	}

	public String getToMailAddr() {
		return toMailAddr;
	}

	public void setToMailAddr(String toMailAddr) {
		this.toMailAddr = toMailAddr;
	}

	public String getFromMailAddr() {
		return fromMailAddr;
	}

	public void setFromMailAddr(String fromMailAddr) {
		this.fromMailAddr = fromMailAddr;
	}



}
