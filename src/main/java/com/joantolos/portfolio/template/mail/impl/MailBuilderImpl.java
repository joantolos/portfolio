package com.joantolos.portfolio.template.mail.impl;

import com.joantolos.portfolio.template.mail.MailBuilder;
import com.joantolos.portfolio.template.entity.ContactMail;
import com.joantolos.portfolio.template.entity.Mail;
import com.joantolos.portfolio.template.exception.FileManipulationException;
import com.joantolos.portfolio.template.utils.FileUtils;
import com.joantolos.portfolio.template.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailBuilderImpl implements MailBuilder {

    @Value("${mail.templates.success.path}")
    private String templateSuccessPath;
    
    @Value("${mail.templates.error.path}")
    private String templateErrorPath;

    @Value("${mail.attach.file.extension}")
    private String attachFileExtension;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    StringUtils stringUtils;

    public Mail buildMail(ContactMail contactMail) throws FileManipulationException {
        Mail mail = new Mail();
        mail.setSubject(this.buildSubject(contactMail));
        mail.setTo(contactMail.getUserMailAddress());
        mail.setAttachName(this.buildAttachName(contactMail));
        mail.setHtmlContent(this.buildHtmlContent(contactMail));
        mail.setAttach(contactMail.getAttach());

        return mail;
    }

    private String buildSubject(ContactMail contactMail) {
        StringBuilder sb = new StringBuilder("Here we have the email about ");
        sb.append(contactMail.getTopic());
        return sb.toString();
    }

    private String buildAttachName(ContactMail contactMail) {
        StringBuilder sb = new StringBuilder(stringUtils.toCamelCase(contactMail.getTopic()," "));
        sb.append(this.attachFileExtension);

        return sb.toString();
    }

    private String buildHtmlContent(ContactMail contactMail) throws FileManipulationException {
    	String body;
    	
    	if(contactMail.isSuccess()){
	        body = fileUtils.streamToString(this.getClass().getResourceAsStream(this.templateSuccessPath));
    	}else{
    		body = fileUtils.streamToString(this.getClass().getResourceAsStream(this.templateErrorPath));
    		body = body.replace("#errorMessage","ERROR!!");
    	}

        body = body.replace("#userName", contactMail.getUserName());
        
        return body;
    }
}
