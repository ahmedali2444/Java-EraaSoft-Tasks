package services.implementation;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.OtpPurpose;
import services.EmailNotificationService;
import util.EnvironmentConfiguration;

public class SmtpEmailNotificationService implements EmailNotificationService {

    @Override
    public boolean sendOtp(String recipientEmail, String otp, OtpPurpose purpose) {
        String action = purpose == OtpPurpose.LOGIN ? "sign in to your wallet" : "create your wallet account";
        String subject = purpose == OtpPurpose.LOGIN ? "Your E-Wallet sign-in code" : "Verify your E-Wallet account";
        String content = "<p>Use this secure code to " + action + ".</p>"
                + "<div style=\"margin:24px 0;padding:16px 20px;border-radius:12px;background:#161b22;"
                + "border:1px solid #30363d;color:#3fb950;font-size:28px;font-weight:700;letter-spacing:8px;\">"
                + otp + "</div>"
                + "<p>This code expires in 10 minutes. Do not share it with anyone.</p>";
        return send(recipientEmail, subject, content);
    }

    @Override
    public boolean sendTransferReceived(String recipientEmail, String senderEmail, double amount, double newBalance) {
        String content = "<p>You received a transfer from <strong>" + escapeHtml(senderEmail) + "</strong>.</p>"
                + "<div style=\"margin:24px 0;padding:16px 20px;border-radius:12px;background:#161b22;"
                + "border:1px solid #30363d;color:#3fb950;font-size:24px;font-weight:700;\">$ "
                + String.format(Locale.US, "%,.2f", amount) + "</div>"
                + "<p>Your new wallet balance is <strong>$ "
                + String.format(Locale.US, "%,.2f", newBalance) + "</strong>.</p>";
        return send(recipientEmail, "You received money in E-Wallet", content);
    }

    private boolean send(String recipientEmail, String subject, String content) {
        String host = EnvironmentConfiguration.get("SMTP_HOST");
        String port = EnvironmentConfiguration.getOrDefault("SMTP_PORT", "587");
        String username = EnvironmentConfiguration.get("SMTP_USERNAME");
        String configuredPassword = EnvironmentConfiguration.get("SMTP_PASSWORD");
        String senderName = EnvironmentConfiguration.getOrDefault("SMTP_FROM_NAME", "E-Wallet");
        final String password = configuredPassword == null ? null : configuredPassword.replaceAll("\\s+", "");

        if (isBlank(host) || isBlank(username) || isBlank(password) || isBlank(recipientEmail)) {
            return false;
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.connectiontimeout", "10000");
        properties.put("mail.smtp.timeout", "10000");
        properties.put("mail.smtp.writetimeout", "10000");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, senderName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, true));
            message.setSubject(subject);
            message.setContent(emailLayout(content), "text/html; charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (MessagingException | UnsupportedEncodingException exception) {
            System.err.println("SMTP email delivery failed: " + exception.getClass().getSimpleName()
                    + " - " + exception.getMessage());
            return false;
        }
    }

    private String emailLayout(String content) {
        return "<!doctype html><html><body style=\"margin:0;background:#0d1117;color:#f0f6fc;"
                + "font-family:Arial,sans-serif;line-height:1.55\"><div style=\"max-width:560px;margin:0 auto;"
                + "padding:36px 24px\"><div style=\"padding:28px;border:1px solid #30363d;border-radius:18px;"
                + "background:#161b22\"><h1 style=\"margin:0 0 18px;font-size:24px\">E-Wallet</h1>"
                + content + "<p style=\"margin:24px 0 0;color:#8b949e;font-size:12px\">"
                + "This is an automated E-Wallet notification.</p></div></div></body></html>";
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
