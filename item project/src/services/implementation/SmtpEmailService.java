package services.implementation;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import services.EmailService;
import util.EnvironmentConfiguration;

public class SmtpEmailService implements EmailService {
    @Override
    public boolean sendOtp(String recipient, String code) {
        String host = EnvironmentConfiguration.get("SMTP_HOST");
        String port = EnvironmentConfiguration.getOrDefault("SMTP_PORT", "587");
        String username = EnvironmentConfiguration.get("SMTP_USERNAME");
        String configuredPassword = EnvironmentConfiguration.get("SMTP_PASSWORD");
        String password = configuredPassword == null ? null : configuredPassword.replaceAll("\\s+", "");
        if (isBlank(host) || isBlank(username) || isBlank(password)) return false;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");
        properties.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, EnvironmentConfiguration.getOrDefault("SMTP_FROM_NAME", "Item Project")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Your Item Project verification code");
            message.setContent("<div style='background:#263942;color:#ecf4f5;padding:32px;font-family:Arial;border-radius:18px'><h2>Item Project</h2><p>Use this code to open your dashboard:</p><strong style='font-size:30px;letter-spacing:8px'>" + code + "</strong><p style='color:#a9bac0'>The code expires in 10 minutes.</p></div>", "text/html; charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (Exception exception) {
            System.err.println("OTP email failed: " + exception.getMessage());
            return false;
        }
    }
    private boolean isBlank(String value) { return value == null || value.trim().isEmpty(); }
}
