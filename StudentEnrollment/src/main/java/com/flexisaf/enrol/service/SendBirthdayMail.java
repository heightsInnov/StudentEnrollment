package com.flexisaf.enrol.service;

import com.flexisaf.enrol.utility.PBEncrytor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class SendBirthdayMail {

    private static String username;
    private static String password;
    private static String host;
    private static String port;

    private final PBEncrytor encryptors;

    @Autowired
    public SendBirthdayMail(PBEncrytor encryptors) {
        this.encryptors = encryptors;
    }

    public void sendEMail(List<String> studentEmails) {

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", encryptors.PBDecrypt(host));
        properties.put("mail.smtp.port", encryptors.PBDecrypt(port));
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(encryptors.PBDecrypt(username), encryptors.PBDecrypt(password));
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header.
            InternetAddress[] addresses = new InternetAddress[studentEmails.size()];
            for (int i = 0; i < studentEmails.size(); i++) {
                addresses[i] = new InternetAddress(studentEmails.get(i));
            }
            message.addRecipients(Message.RecipientType.TO, addresses);

            // Set Subject: header field
            message.setSubject("Happy Birthday");

            // Now set the actual message
            message.setText("Happy birthday to you on this day");

            System.out.println("sending mail...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
