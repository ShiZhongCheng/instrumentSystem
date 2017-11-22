package api.mail;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
	/* 邮件服务器地址和验证参数 */
	private String host = "smtp.163.com";
	private String username = "18258205329@163.com";
	private String password = "szc2454715873";

	/* 发送者 */
	private String sender = "18258205329@163.com";
	/* 签名 */
	private String signature = "检修系统";

	/* 第一个参数为邮件主题，第二个参数为邮件内容，第三个参数为接收者地址 */
	public String sendMail(String subject, String content, String recipient) {
		/* 获取系统属性对象 */
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		/* 获取默认的Session对象。 */
		Session mailsession = Session.getDefaultInstance(props);
		boolean sessionDebug = false;
		mailsession.setDebug(sessionDebug);

		try {
			/* 创建一个默认的MimeMessage对象 */
			MimeMessage message = new MimeMessage(mailsession);
			
			// 将个性签名进行编码，避免中文乱码
			try {    
				signature=javax.mail.internet.MimeUtility.encodeText(signature);    
	        } catch (UnsupportedEncodingException e) {    
	            e.printStackTrace();    
	        } 
			
			message.setFrom(new InternetAddress(signature + "<" + sender + ">"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject, "UTF-8");
			/*设置 HTML消息*/
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
