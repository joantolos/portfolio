package com.joantolos.portfolio.template.controller;

import com.joantolos.portfolio.template.entity.ContactMail;
import com.joantolos.portfolio.template.exception.MailServiceException;
import com.joantolos.portfolio.template.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@RestController
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    MailSender mailSender;

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public void contact(final HttpServletRequest request) throws MailServiceException {
        ContactMail contactMail =  new ContactMail();
        this.mailSender.sendMail(contactMail);
    }
}
