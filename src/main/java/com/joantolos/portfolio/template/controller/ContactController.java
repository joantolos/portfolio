package com.joantolos.portfolio.template.controller;

import com.joantolos.portfolio.template.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@RestController
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    MailSender mailSender;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView ping(final HttpServletRequest request) throws IOException {
        ModelAndView response = new ModelAndView("contact");
        response.addObject("pingResponse", "pong");

        return response;
    }
}
