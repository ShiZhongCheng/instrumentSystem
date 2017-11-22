package api.mail;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
	/* �ʼ���������ַ����֤���� */
	private String host = "smtp.163.com";
	private String username = "18258205329@163.com";
	private String password = "szc2454715873";

	/* ������ */
	private String sender = "18258205329@163.com";
	/* ǩ�� */
	private String signature = "����ϵͳ";

	/* ��һ������Ϊ�ʼ����⣬�ڶ�������Ϊ�ʼ����ݣ�����������Ϊ�����ߵ�ַ */
	public String sendMail(String subject, String content, String recipient) {
		/* ��ȡϵͳ���Զ��� */
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		/* ��ȡĬ�ϵ�Session���� */
		Session mailsession = Session.getDefaultInstance(props);
		boolean sessionDebug = false;
		mailsession.setDebug(sessionDebug);

		try {
			/* ����һ��Ĭ�ϵ�MimeMessage���� */
			MimeMessage message = new MimeMessage(mailsession);
			
			// ������ǩ�����б��룬������������
			try {    
				signature=javax.mail.internet.MimeUtility.encodeText(signature);    
	        } catch (UnsupportedEncodingException e) {    
	            e.printStackTrace();    
	        } 
			
			message.setFrom(new InternetAddress(signature + "<" + sender + ">"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject, "UTF-8");
			/*���� HTML��Ϣ*/
			message.setContent(content,"text/html;charset=UTF-8");
			message.saveChanges(); 
		    
		    Transport transport=mailsession.getTransport("smtp");  
		    transport.connect(host, username, password);  
		    transport.sendMessage(message, message.getAllRecipients());  
		    transport.close();

			return "succes";
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return "error";
		}
	}
}
