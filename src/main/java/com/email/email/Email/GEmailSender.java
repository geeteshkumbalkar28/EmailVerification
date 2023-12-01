package com.email.email.Email;



import com.email.email.Model.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;
@Service
public class GEmailSender {

    public boolean sendEmail(User user){

        String emailTemplate= loadEmailTemplate("otp_email_template.html");
        String otp = Integer.toString(new Random().nextInt(9999));
        user.setOtp(otp);
         emailTemplate=emailTemplate.replace("{{otp}}",otp);
















        boolean flag =false;

        Properties properties = System.getProperties();

        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.ssl.enable",true);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.host","smtp.gmail.com");

        String userName = "testOne28062000";
        String password = "feczhxfltmenkgne";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        });

        try{

            MimeMessage message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
            message.setFrom("testOne28062000@gmail.com");
            message.setSubject("OTP Verification");
            message.setContent(emailTemplate,"text/html");

            Transport.send(message);
        user.setvStatus(false);
            flag = true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    String loadEmailTemplate(String templateFileName) {
        try
        { InputStream inputStream = getClass().getResourceAsStream("/templates/" + templateFileName);
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    return reader.lines().collect(Collectors.joining(System.lineSeparator())); } }
            else
            {
                throw new IOException("Template file not found: " + templateFileName); }
        } catch
        (IOException e) {
            e.printStackTrace();throw new RuntimeException("Error loading email template");
        }
    }
}
