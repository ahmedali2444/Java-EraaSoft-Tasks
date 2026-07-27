package services.implementation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import models.OtpChallenge;
import models.OtpIssue;
import models.OtpVerificationStatus;
import services.OtpVerificationService;

public class OtpVerificationServiceImpl implements OtpVerificationService {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final long OTP_LIFETIME_MILLIS = 10 * 60 * 1000;

    @Override
    public OtpIssue issue() {
        String code = String.format("%06d", RANDOM.nextInt(1_000_000));
        long issuedAt = System.currentTimeMillis();
        OtpChallenge challenge = new OtpChallenge(hash(code), issuedAt, issuedAt + OTP_LIFETIME_MILLIS);
        return new OtpIssue(challenge, code);
    }

    @Override
    public OtpVerificationStatus verify(OtpChallenge challenge, String otp) {
        if (challenge == null || otp == null || !otp.matches("\\d{6}")) {
            return OtpVerificationStatus.INVALID;
        }
        if (challenge.isExpired()) {
            return OtpVerificationStatus.EXPIRED;
        }
        if (challenge.isLocked()) {
            return OtpVerificationStatus.LOCKED;
        }
        if (MessageDigest.isEqual(challenge.getCodeHash(), hash(otp))) {
            return OtpVerificationStatus.VERIFIED;
        }

        challenge.registerFailedAttempt();
        return challenge.isLocked() ? OtpVerificationStatus.LOCKED : OtpVerificationStatus.INVALID;
    }

    private byte[] hash(String value) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("OTP hashing is unavailable.", exception);
        }
    }
}
