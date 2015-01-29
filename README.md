# Portfolio Template
Simple Portfolio Template with Spring MVC and Bootstrap.

# Architecture 
This is a simple "all-in-one" architecture with only one maven module that makes it ideal to deploy in cloud services like Heroku:

```
https://www.heroku.com/
```

Where is easier to deploy an application with a single war file than more than one.

So, we have the "controller" package with the entry point from the front end where you can map all the logic needed. The only one this site has as an example is the "contact" section which sends an email. It uses the smtp from a free gmail account.

# Template

The Bootstrap template is free an you can find it on:

```
http://startbootstrap.com/template-overviews/agency/
```

# Start Hacking

You can start playing with the template editing the "index.jsp" page since it is a "one-page" template. The first controller you want to see is ContactController.
