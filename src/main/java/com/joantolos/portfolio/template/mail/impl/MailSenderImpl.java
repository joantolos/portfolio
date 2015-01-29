package com.joantolos.portfolio.template.mail.impl;

import com.joantolos.portfolio.template.entity.ContactMail;
import com.joantolos.portfolio.template.entity.Mail;
import com.joantolos.portfolio.template.exception.FileManipulationException;
import com.joantolos.portfolio.template.exception.MailServiceException;
import com.joantolos.portfolio.template.mail.MailBuilder;
import com.joantolos.portfolio.template.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.joantolos.portfolio.template.mail.MailSender;

import java.io.IOException;
import java.util.Properties;

@Component
public class MailSenderImpl implements MailSender {

    private Properties props;
    
    @Value("${mail.user.from}")
    private String userFrom;
    @Value("${mail.password.from}")
    private String passwordFrom;
    @Value("${mail.html.charset}")
    private String htmlCharset;
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;
    @Value("${mail.attach.file.extension}")
    private String attachedFileType;
    
    @Autowired
    private FileUtils fileUtils;
    
    @Autowired
    MailBuilder mailBuilder;

    @PostConstruct
    public void init(){
        props = new Properties();
        props.put("mail.smtp.starttls.enable", starttls);
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
    }

    public Message sendMail(ContactMail contactMail) throws MailServiceException {
        Message message;
        
        try {
            Mail mail = this.mailBuilder.buildMail(contactMail);
            Session session = this.getSession();
            
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.userFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setContent(this.createMailContent(mail));

            Transport.send(message);
            
        } catch (MessagingException | IOException | FileManipulationException e) {
            throw new MailServiceException(e.getMessage());
        }

        return message;
    }

    private Session getSession(){
        Session session;

        if (props.getProperty("mail.smtp.starttls.enable").equals("false")){
            session = Session.getInstance(props);
        } else {
            session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(userFrom, passwordFrom);
                        }
                    });
        }

        return session;
    }
    
    private Multipart createMailContent(Mail mail) throws MessagingException, IOException, FileManipulationException {        
        // Html part
        Multipart multipart = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(mail.getHtmlContent(), this.htmlCharset);

        // Attach part
        if(mail.hasAttach()) {
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.setFileName(mail.getAttachName());
            attachPart.attachFile(this.fileUtils.byteArrayToFile(mail.getAttach(), mail.getAttachName()));

            multipart.addBodyPart(attachPart);
        }

        multipart.addBodyPart(htmlPart);
        
        return multipart;
    }
}
