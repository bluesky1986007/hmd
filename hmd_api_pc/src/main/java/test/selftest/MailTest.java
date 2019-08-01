package test.selftest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class MailTest extends javax.mail.Authenticator{

  @BeforeClass
  public void beforeClass() {
  }
  
  @Test(enabled=true)
  public void mail() throws IOException {
	  
	  Calendar c = Calendar.getInstance(); 
	  SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy kk:mm");
	  
	  String from = "luyi@xinlonghang.cn";
	  
	  String to = "luyi@xinlonghang.cn";
	  
	  String project = "xlhAppAutoTest";
	  
	  String pathString = String.valueOf(new File("./").getCanonicalPath()) + "\\test-output\\emailable-report.html";
	  System.out.println("report路径为： "+pathString);
	  
	  sendMail(from,to,"Test Results for " + project + " " + sdf.format(c.getTime()),pathString);
  }
  
  
  public void sendMail(String fromAddr, String toMailAddr, String asubject, String attach){
	  
	  String host ="smtp.qiye.163.com";
	  final String username="luyi@xinlonghang.cn";
	  final String password="33224466Ab";
	  
	  Properties properties = System.getProperties();
	  
	  properties.setProperty("mail.smtp.host", host);
	  properties.setProperty("mail.smtp.auth", "true");
	  
	  
	  Session session = Session.getDefaultInstance(properties,new Authenticator(){ 
		  protected PasswordAuthentication getPasswordAuthentication() { 
			  return new PasswordAuthentication(username, password); 
			  }});
	  
	  MimeMessage message = new MimeMessage(session);
	  
	  try {
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailAddr));
		message.setFrom(new InternetAddress(fromAddr));
		
		message.setSubject(asubject);
		
		MimeBodyPart attachFilePart = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(attach);
		attachFilePart.setDataHandler(new DataHandler(fds));
		attachFilePart.setFileName(fds.getName());

//		textPart.setContent(attachFilePart, "text/html");
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setText("AutomationTest results for android platform is refered to the attachment!");

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(attachFilePart);
		mp.addBodyPart(textPart);

		
		message.setContent(mp);
		Transport.send(message);
		
		System.out.println("Mail was sent to "+toMailAddr);
		
		
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
  

  @AfterClass
  public void afterClass() {
  }

}
