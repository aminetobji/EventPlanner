/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfinal.util;
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import java.io.FileReader;

public class MailModule {
    protected final String user="Hajbrahem.habib@outlook.com";
    protected final String password="javafxt3aaab"; 
    protected final String host="smtp-mail.outlook.com";
    final String contentType = "text/html; charset=utf-8";
    private String subject;
    private String text;
    private String content;

    public void send(String to) {  //Get the session object  
        Properties props = new Properties();  
        props.put("mail.smtp.host",host);  
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.auth", "true");  
            Session session = Session.getDefaultInstance(props,  
            new javax.mail.Authenticator() {  
                protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(user,password);  
                }
            });
             try {  
                MimeMessage message = new MimeMessage(session);  
                message.setFrom(new InternetAddress(user));  
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
                message.setSubject(subject);  
                message.setText(text);
                message.setContent(content,contentType);
                Transport.send(message);  
                System.out.println("message sent successfully... to "+to);  
            } catch (MessagingException e) {
                System.err.println(e.fillInStackTrace());
            }  
    
        }  
    }  
        
      
        
       
      
        
      
      
      
         
       