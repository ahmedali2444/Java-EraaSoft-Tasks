package services;
public interface EmailService {
    boolean sendOtp(String email, String code);
}
