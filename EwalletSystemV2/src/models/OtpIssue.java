package models;

public class OtpIssue {

    private final OtpChallenge challenge;
    private final String code;

    public OtpIssue(OtpChallenge challenge, String code) {
        this.challenge = challenge;
        this.code = code;
    }

    public OtpChallenge getChallenge() {
        return challenge;
    }

    public String getCode() {
        return code;
    }
}
