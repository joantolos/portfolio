package com.joantolos.portfolio.template.mail;

import com.joantolos.portfolio.template.entity.Mail;
import com.joantolos.portfolio.template.exception.MailServiceException;

import javax.mail.Message;

public interface MailSender {

    public Message sendMail(Mail mail) throws MailServiceException;
}

