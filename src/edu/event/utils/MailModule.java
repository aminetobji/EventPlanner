/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailModule {

    protected final String user = "Hajbrahem.habib@outlook.com";
    protected final String password = "javafxt3aaab";
    protected final String host = "smtp-mail.outlook.com";
    final String contentType = "text/html; charset=utf-8";
    private String subject;
    private String text;
    private String content;

    public void send(String to) {  //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            message.setContent(content, contentType);
            Transport.send(message);
            System.out.println("message sent successfully... to " + to);
        } catch (MessagingException e) {
            System.err.println(e.fillInStackTrace());
        }

    }

    public static void sendMailAmine(String recepient, String Content) throws Exception {
        System.out.println("Envoi des emails en cours de traitement");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "amirzaafouri1@gmail.com";
        String password = "iprtbksfwopntaxn";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessageAmir(session, myAccountEmail, recepient, Content);

        Transport.send(message);
        System.out.println("Message envoyée");

    }

    private static Message prepareMessageAmir(Session session, String myAccountEmail, String recepient, String Content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Change Password");
            message.setText(Content);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
