package services;

import models.OtpPurpose;

public interface EmailNotificationService {

    boolean sendOtp(String recipientEmail, String otp, OtpPurpose purpose);

    boolean sendTransferReceived(String recipientEmail, String senderEmail, double amount, double newBalance);
}
