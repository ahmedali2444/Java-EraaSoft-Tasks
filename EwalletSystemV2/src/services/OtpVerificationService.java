package services;

import models.OtpIssue;
import models.OtpChallenge;
import models.OtpVerificationStatus;

public interface OtpVerificationService {

    OtpIssue issue();

    OtpVerificationStatus verify(OtpChallenge challenge, String otp);
}
