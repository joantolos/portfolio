package com.joantolos.portfolio.template.mail;

import com.joantolos.portfolio.template.entity.ContactMail;
import com.joantolos.portfolio.template.exception.MailServiceException;

import javax.mail.Message;

public interface MailSender {

    public Message sendMail(ContactMail mail) throws MailServiceException;
}

