package services.implementation;

import services.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public Boolean isEmailValid(String email) {
        return getEmailValidationMessage(email) == null;
    }

    @Override
    public String getEmailValidationMessage(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Enter a valid email address.";
        }

        return null;
    }

    @Override
    public Boolean isPasswordValid(String password) {
        return getPasswordValidationMessage(password) == null;
    }

    @Override
    public String getPasswordValidationMessage(String password) {
        StringBuilder missingRequirements = new StringBuilder();
        String currentPassword = password == null ? "" : password;

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;

        for (int i = 0; i < currentPassword.length(); i++) {
            char currentChar = currentPassword.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(currentChar)) {
                hasLowerCase = true;
            } else if (Character.isDigit(currentChar)) {
                hasDigit = true;
            }
        }

        if (currentPassword.length() < 8) {
            addMissingRequirement(missingRequirements, "at least 8 characters");
        }
        if (!hasUpperCase) {
            addMissingRequirement(missingRequirements, "an uppercase letter");
        }
        if (!hasLowerCase) {
            addMissingRequirement(missingRequirements, "a lowercase letter");
        }
        if (!hasDigit) {
            addMissingRequirement(missingRequirements, "a number");
        }
        if (missingRequirements.length() == 0) {
            return null;
        }

        return "Password needs: " + missingRequirements + ".";
    }

    private void addMissingRequirement(StringBuilder missingRequirements, String requirement) {
        if (missingRequirements.length() > 0) {
            missingRequirements.append(", ");
        }
        missingRequirements.append(requirement);
    }

    @Override
    public Boolean isAgeValid(int age) {
        return getAgeValidationMessage(age) == null;
    }

    @Override
    public String getAgeValidationMessage(int age) {
        if (age < 18) {
            return "Age must be 18 or older.";
        }

        return null;
    }

    @Override
    public Boolean isPhoneNumberValid(String phoneNumber) {
        return getPhoneNumberValidationMessage(phoneNumber) == null;
    }

    @Override
    public String getPhoneNumberValidationMessage(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Phone number cannot be empty.";
        }

        if (!phoneNumber.matches("\\+\\d{10,15}")) {
            return "Phone number must start with + and contain 10 to 15 numbers only.";
        }

        return null;
    }
}
