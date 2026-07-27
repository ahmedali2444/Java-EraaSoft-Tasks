package services;

import services.implementation.AccountServiceImpl;
import services.implementation.OtpVerificationServiceImpl;
import services.implementation.SmtpEmailNotificationService;

public class WalletServiceProvider {

    private static final EmailNotificationService emailNotificationService = new SmtpEmailNotificationService();
    private static final OtpVerificationService otpVerificationService = new OtpVerificationServiceImpl();
    private static final AccountService accountService = new AccountServiceImpl(emailNotificationService);

    public static AccountService getAccountService() {
        return accountService;
    }

    public static EmailNotificationService getEmailNotificationService() {
        return emailNotificationService;
    }

    public static OtpVerificationService getOtpVerificationService() {
        return otpVerificationService;
    }
}
