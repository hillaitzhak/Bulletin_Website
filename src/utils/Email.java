package utils;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Email {
	   final String senderEmailID = "hilaadihilaadi@gmail.com";
	   final String senderPassword = "hilaadihilaadi!";
	   final String emailSMTPserver = "smtp.gmail.com";
	   final String emailServerPort = "465";
	   private String receiverEmailID;
	   private String emailSubject;
	   private String emailBody;
	   
	   public Email(String receiverEmailID, String Subject, String Body) {
		super();
		// Receiver Email Address
		  this.receiverEmailID=receiverEmailID; 
		  // Subject
		  this.emailSubject=Subject;
		  // Body
		  this.emailBody=Body;
		  Properties props = new Properties();
		  props.put("mail.smtp.user",senderEmailID);
		  props.put("mail.smtp.host", emailSMTPserver);
		  props.put("mail.smtp.port", emailServerPort);
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.socketFactory.port", emailServerPort);
		  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		  props.put("mail.smtp.socketFactory.fallback", "false");
		  SecurityManager security = System.getSecurityManager();
		  try{  
		  Authenticator auth = new SMTPAuthenticator();
		  Session session = Session.getInstance(props, auth);
		  MimeMessage msg = new MimeMessage(session);
		  msg.setText(emailBody);
		  msg.setSubject(emailSubject);
		  msg.setFrom(new InternetAddress(senderEmailID));
		  msg.addRecipient(Message.RecipientType.TO,
		  new InternetAddress(receiverEmailID));
		  Transport.send(msg);
		  System.out.println("Message send Successfully:)"); }
		  
		  catch (Exception mex){
		  mex.printStackTrace();}
		  
		  
	}

	public String getReceiverEmailID() {
		return receiverEmailID;
	}

	public void setReceiverEmailID(String receiverEmailID) {
		this.receiverEmailID = receiverEmailID;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public class SMTPAuthenticator extends javax.mail.Authenticator
	{
	  public PasswordAuthentication getPasswordAuthentication()
	  {
		  return new PasswordAuthentication(senderEmailID, senderPassword);
	  }

	 }
}
