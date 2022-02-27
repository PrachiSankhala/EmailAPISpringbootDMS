package com.example.email.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendEmail(String subject,String message,String to) {

        boolean f = false;
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES" + properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abcd@gmail.com","password");
            }
        });

        session.setDebug(true);
        MimeMessage m =  new MimeMessage(session);

        try {
            m.setFrom();
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            m.setSubject(subject);
            m.setText(message);

            Transport.send(m);
            System.out.println("Email send successfully...");
            f = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
    
    
    public String getMessage(int code) {

        String message = "";

        switch (code) {

            case 1 :

                  message = "We appreciate your interest in taking Distributorship , But unfortunately your request has been rejected by admin";

                  break;

            case 2 :

                message = "Congratulations !! Your request for Distributorship has been approved";

                break;


            case 3 :

                message = "We appreciate your interest in taking Retailership , But unfortunately your request has been rejected by Distributor";


                break;

            case 4 :

                message = "Congratulations !! Your request for Retailership has been approved";


                break;

            case 5 :

                message = "Your Order has been approved by Distributor";


                break;

            case 6 :

                message = "Sorry !! Your Order has been rejected by Distributor";
        }

        return message;

    }

    public String getSubject(int code) {

        String subject = "";

        if(code == 1 || code == 2) {

            subject = "Regarding Distributorship";

        }


        else if( code ==5 || code==6) {

            subject = "Regarding Order";

        }

        else if(code == 3 || code == 4) {

            subject = "Regarding Retailership";

        }

        return subject;

    }


}


