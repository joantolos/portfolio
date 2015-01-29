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
    public void contact(final HttpServletRequest request, String name, String phone, String email, String message) throws MailServiceException {
        ContactMail contactMail =  new ContactMail();
        contactMail.setUserName(name);
        contactMail.setSuccess(true);
        contactMail.setUserMailAddress(email);
        contactMail.setAttach(null);
        contactMail.setMailDate("one date");
        contactMail.setMailId(1L);
        contactMail.setTopic(message);
        
        this.mailSender.sendMail(contactMail);
    }
}
