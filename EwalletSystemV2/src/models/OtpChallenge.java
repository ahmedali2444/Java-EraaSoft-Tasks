package models;

public class OtpChallenge {

    private final byte[] codeHash;
    private final long issuedAtMillis;
    private final long expiresAtMillis;
    private int failedAttempts;

    public OtpChallenge(byte[] codeHash, long issuedAtMillis, long expiresAtMillis) {
        this.codeHash = codeHash.clone();
        this.issuedAtMillis = issuedAtMillis;
        this.expiresAtMillis = expiresAtMillis;
    }

    public byte[] getCodeHash() {
        return codeHash.clone();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAtMillis;
    }

    public long getIssuedAtMillis() {
        return issuedAtMillis;
    }

    public long getExpiresAtMillis() {
        return expiresAtMillis;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public boolean canResend() {
        return System.currentTimeMillis() - issuedAtMillis >= 30_000;
    }

    public boolean isLocked() {
        return failedAttempts >= 5;
    }

    public void registerFailedAttempt() {
        failedAttempts++;
    }
}
