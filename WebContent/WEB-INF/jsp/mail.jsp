<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@page import="java.util.*"%>  
<%@page import="javax.mail.*"%>  
<%@page import="javax.mail.internet.*"%>  
<%@page import="javax.activation.*"%>  
  
<%  
      
    /* 
    sina.com:  
    POP3服务器地址:pop3.sina.com.cn（端口：110） 
    SMTP服务器地址:smtp.sina.com.cn（端口：25）  
     
    163.com:  
    POP3服务器地址:pop.163.com（端口：110） 
    SMTP服务器地址:smtp.163.com（端口：25） 
    */  
      
    String recipient="2454715873@qq.com";  
    String sender="18258205329@163.com";  
    String subject="最近还好吗";  
    String text="最近还好吗，我是你的老朋友jack！";  
      
    String host="smtp.163.com";  
    String username="18258205329@163.com";  
    String password="szc2454715873";  
      
    Properties props=new Properties();  
      
    props.put("mail.smtp.host",host);  
    props.put("mail.smtp.port","25");  
    props.put("mail.smtp.auth","true");  
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
      
    Session mailsession=Session.getDefaultInstance(props);  
    boolean sessionDebug=false;  
    mailsession.setDebug(sessionDebug);  
      
    MimeMessage message=new MimeMessage(mailsession);  
      
    message.setFrom(new InternetAddress(sender));  
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));  
    message.setSubject(subject, "UTF-8");  
    message.setText(text);  
    message.saveChanges();  
      
    Transport transport=mailsession.getTransport("smtp");  
    transport.connect(host, username, password);  
    transport.sendMessage(message, message.getAllRecipients());  
    transport.close();      
  
%>  
  
<!DOCTYPE html>  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>sendmail</title>  
    </head>  
    <body>  
        <h1>邮件已经发送！</h1>  
    </body>  
</html>  