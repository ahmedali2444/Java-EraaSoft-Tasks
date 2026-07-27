package util;

import javax.servlet.http.HttpSession;

import models.Account;
import models.OtpChallenge;
import models.OtpIssue;
import models.OtpPurpose;

public final class SessionData {

    private static final String LOGGED_IN_EMAIL = "loggedInEmail";
    private static final String OTP_EMAIL = "otpEmail";
    private static final String OTP_PASSWORD = "otpPassword";
    private static final String OTP_PHONE = "otpPhone";
    private static final String OTP_AGE = "otpAge";
    private static final String OTP_PURPOSE = "otpPurpose";
    private static final String OTP_CODE_HASH = "otpCodeHash";
    private static final String OTP_ISSUED_AT = "otpIssuedAt";
    private static final String OTP_EXPIRES_AT = "otpExpiresAt";
    private static final String OTP_FAILED_ATTEMPTS = "otpFailedAttempts";

    private SessionData() {
    }

    public static String getLoggedInEmail(HttpSession session) {
        if (session == null) {
            return null;
        }
        Object email = session.getAttribute(LOGGED_IN_EMAIL);
        return email instanceof String && !((String) email).trim().isEmpty() ? (String) email : null;
    }

    public static void setLoggedInEmail(HttpSession session, String email) {
        session.setAttribute(LOGGED_IN_EMAIL, email);
    }

    public static void clearLoggedInEmail(HttpSession session) {
        session.removeAttribute(LOGGED_IN_EMAIL);
    }

    public static void savePendingOtp(HttpSession session, Account account, OtpPurpose purpose, OtpIssue issue) {
        OtpChallenge challenge = issue.getChallenge();
        session.setAttribute(OTP_EMAIL, account.getEmail());
        session.setAttribute(OTP_PASSWORD, account.getPassword());
        session.setAttribute(OTP_PHONE, account.getPhoneNumber());
        session.setAttribute(OTP_AGE, account.getAge());
        session.setAttribute(OTP_PURPOSE, purpose.name());
        saveOtpChallenge(session, challenge);
    }

    public static Account getPendingAccount(HttpSession session) {
        String email = getString(session, OTP_EMAIL);
        String password = getString(session, OTP_PASSWORD);
        String phone = getString(session, OTP_PHONE);
        Object age = session == null ? null : session.getAttribute(OTP_AGE);
        if (email == null || password == null || phone == null || !(age instanceof Integer)) {
            return null;
        }
        return new Account(email, password, phone, (Integer) age);
    }

    public static OtpPurpose getPendingPurpose(HttpSession session) {
        String purpose = getString(session, OTP_PURPOSE);
        if (purpose == null) {
            return null;
        }
        try {
            return OtpPurpose.valueOf(purpose);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public static OtpChallenge getPendingChallenge(HttpSession session) {
        if (session == null) {
            return null;
        }
        Object codeHash = session.getAttribute(OTP_CODE_HASH);
        Object issuedAt = session.getAttribute(OTP_ISSUED_AT);
        Object expiresAt = session.getAttribute(OTP_EXPIRES_AT);
        Object failedAttempts = session.getAttribute(OTP_FAILED_ATTEMPTS);
        if (!(codeHash instanceof byte[]) || !(issuedAt instanceof Long) || !(expiresAt instanceof Long)
                || !(failedAttempts instanceof Integer)) {
            return null;
        }
        OtpChallenge challenge = new OtpChallenge((byte[]) codeHash, (Long) issuedAt, (Long) expiresAt);
        for (int attempt = 0; attempt < (Integer) failedAttempts; attempt++) {
            challenge.registerFailedAttempt();
        }
        return challenge;
    }

    public static void saveOtpChallenge(HttpSession session, OtpChallenge challenge) {
        session.setAttribute(OTP_CODE_HASH, challenge.getCodeHash());
        session.setAttribute(OTP_ISSUED_AT, challenge.getIssuedAtMillis());
        session.setAttribute(OTP_EXPIRES_AT, challenge.getExpiresAtMillis());
        session.setAttribute(OTP_FAILED_ATTEMPTS, challenge.getFailedAttempts());
    }

    public static void clearPendingOtp(HttpSession session) {
        session.removeAttribute(OTP_EMAIL);
        session.removeAttribute(OTP_PASSWORD);
        session.removeAttribute(OTP_PHONE);
        session.removeAttribute(OTP_AGE);
        session.removeAttribute(OTP_PURPOSE);
        session.removeAttribute(OTP_CODE_HASH);
        session.removeAttribute(OTP_ISSUED_AT);
        session.removeAttribute(OTP_EXPIRES_AT);
        session.removeAttribute(OTP_FAILED_ATTEMPTS);
    }

    private static String getString(HttpSession session, String key) {
        if (session == null) {
            return null;
        }
        Object value = session.getAttribute(key);
        return value instanceof String ? (String) value : null;
    }
}
