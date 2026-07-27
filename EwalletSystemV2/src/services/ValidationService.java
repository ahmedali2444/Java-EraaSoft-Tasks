package services;

public interface ValidationService {
    Boolean isEmailValid(String email);

    String getEmailValidationMessage(String email);

    Boolean isPasswordValid(String password);

    String getPasswordValidationMessage(String password);

    Boolean isAgeValid(int age);

    String getAgeValidationMessage(int age);

    Boolean isPhoneNumberValid(String phoneNumber);

    String getPhoneNumberValidationMessage(String phoneNumber);
}
