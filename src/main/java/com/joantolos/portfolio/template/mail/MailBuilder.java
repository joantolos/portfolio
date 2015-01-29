package com.joantolos.portfolio.template.mail;

import com.joantolos.portfolio.template.entity.ContactMail;
import com.joantolos.portfolio.template.entity.Mail;
import com.joantolos.portfolio.template.exception.FileManipulationException;

public interface MailBuilder {
    
    public Mail buildMail(ContactMail contactMail) throws FileManipulationException;
    
}
