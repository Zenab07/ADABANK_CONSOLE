/*package ci.ada.services.impl;
import ci.ada.services.interfaces.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.digester.annotations.FromAnnotationRuleProviderFactory;

import java.util.Properties;


public class EmailServiceImpl implements EmailService {
    private static final String FROM_EMAIL = "zenab04.coulibaly@gmail.com";
    private static final String FROM_PASSWORD = "zrih cpsd jaax veki";

    @Override
    public void update(String message, String recipient) {
        sendEmail(recipient, "Notification bancaire", message);
    }


    public void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}*/


package ci.ada.services.impl;

import ci.ada.services.interfaces.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {

    private final String fromEmail = "zenab04.coulibaly@gmail.com";
    private final String password = "zrih cpsd jaax veki";

    @Override
    public void sendEmail(String destinataire, String sujet, String message) {
        if (destinataire == null || destinataire.trim().isEmpty()) {
            throw new IllegalArgumentException("L'adresse email du destinataire ne peut pas être vide");
        }

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));
            msg.setSubject(sujet);
            msg.setText(message);

            Transport.send(msg);

            System.out.println("Email envoyé avec succès à " + destinataire);

        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }
}

