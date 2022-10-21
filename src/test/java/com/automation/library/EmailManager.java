package com.automation.library;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailManager  
{
	public static Logger log = LoggerFactory.getLogger(EmailManager.class);
	
	private String toAddress = "";
	private String ccAddress = "";
	private String bccAddress = "";
	
	
	
	public static void main(String[] args) 
	{
		EmailManager myEmail = new EmailManager();
		myEmail.toAddress = "frank@gmail.com";
		myEmail.ccAddress = "bibuballack13@gmail.com; bahernady@yahoo.com";
		String subject = "Congrats you got a job offer !";
		String emailBody = "Congratulations !!" + "<br><br>" + "You pass our final interview and we are exited to make you a job offer ! "
		                   + "<br><br><br>" + "Regards, " + "<br>" + "HR Team";
		
		myEmail.sendEmail(subject, emailBody);
	
	}
	
	
	
	public void sendEmail(String subject, String emailBody)
	{
		String host = "smtp.gmail.com";
		String port = "587";
		String userID = "qa.testing.811@gmail.com";
		String userPass = "Muralim811!!";
				List<String> tempAttachments = new ArrayList<String>();
		
		sendEmail(host,port,userID,userPass,subject,emailBody,tempAttachments);
	}
	
	private void sendEmail(String host,String port,final String emailUserID, final String emailUserpass,
			String subject,String emailBody,List<String> attachments)
	{
		try
		{      //SMTP:- simple mail transport protocol
			//set SMTP(mail server) server properties
			Properties prop = new Properties();
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.port",port);
			prop.put("mail.smtp.auth", true);
			prop.put("mail.user", emailUserID);
			prop.put("mail.password", emailUserpass);
			prop.put("mail.smtp.starttls.enable", true);
			log.info("Step1> preparing Email configuration....");
			
			//Create a new Session with Authentication
			Authenticator auth = new Authenticator() 
			{
				public PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(emailUserID, emailUserpass);
				}
				
			};
			
			Session session = Session.getInstance(prop,auth);
			Message msg = new MimeMessage(session);
		
			msg.setFrom(new InternetAddress(emailUserID));
			
			msg.addRecipients(Message.RecipientType.TO, setMultipleEmails(toAddress));
			if(!(ccAddress.isEmpty()) && !(ccAddress.equals(null)))
			{
				msg.addRecipients(Message.RecipientType.CC,setMultipleEmails(ccAddress));
			}
			if(!(bccAddress.isEmpty()) && !(bccAddress.equals(null)))
			{
				msg.addRecipients(Message.RecipientType.BCC,setMultipleEmails(bccAddress));
			}
			
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			
			//create msg parts
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailBody,"text/html");
			//create multi parts
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			//add attachments
			if(attachments.size()>0)
			{
			  for(String singleAttachment: attachments)
			  {
				  MimeBodyPart attachPart =new MimeBodyPart();
			      try
			      {
			    	attachPart.attachFile(singleAttachment);  
			      }catch(Exception e)
			      {
			    	  log.error("Attaching files to email failed...",e);
			      }
			      multipart.addBodyPart(attachPart);
			  }
			}
			log.info("Step2 > Attaching report files and wrror screenshots.....");
			msg.setContent(multipart);
			
			log.info("Step3 > sending emails in progress");
			Transport.send(msg);
			
			log.info("Step4 > sending emails completed.");
			
		}catch(Exception e)
		{
			log.error("Sending email failed");
			log.error("Error",e);
			assertTrue(false);
		}
		
	}
	
	private Address[] setMultipleEmails(String emailAddress)
	{
	 String multipleEmails[] =emailAddress.split(";"); 
		InternetAddress[] addresses = new InternetAddress[multipleEmails.length];
		try
		{
			for(int i =0;i<multipleEmails.length;i++)
			{
				addresses[i] = new InternetAddress(multipleEmails[i].trim());
			}
			
			
		}catch(Exception e)
		{
			log.error("Error",e);
			assertTrue(false);
		}
		
		return  addresses;
	}
	

}
