package services.implementation;

import models.Account;
import models.EWalletSystem;
import services.AccountService;
import services.ApplicationService;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {

    // Holds the application metadata that is displayed to the user.
    private final EWalletSystem eWalletSystem = new EWalletSystem();
    // Delegates account creation and login checks to the account service layer.
    private final AccountService accountService = new AccountServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    // Reads the username safely from the console.
    private String readUserName() {
        while (true) {
            System.out.print("Enter username: ");
            String userName = scanner.nextLine().trim();

            if (!userName.isEmpty()) {
                return userName;
            }

            System.out.println("Username cannot be empty. Please try again.");
        }
    }

    // Reads the password safely from the console.
    private String readPassword() {
        while (true) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            if (!password.isEmpty()) {
                return password;
            }

            System.out.println("Password cannot be empty. Please try again.");
        }
    }

    // Collects all signup inputs after validating each field individually.
    private Account readSignupData() {
        String userName = readUserName();
        String password = readPassword();
        String phoneNumber;
        int age;

        while (true) {
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine().trim();

            if (phoneNumber.matches("\\d{10,15}")) {
                break;
            }

            System.out.println("Phone number must contain digits only and be 10 to 15 digits long.");
        }

        while (true) {
            System.out.print("Enter age: ");
            String ageInput = scanner.nextLine().trim();

            try {
                age = Integer.parseInt(ageInput);

                if (age <= 0 || age > 120) {
                    System.out.println("Age must be between 1 and 120.");
                    continue;
                }

                break;
            } catch (NumberFormatException exception) {
                System.out.println("Age must be a valid number. Please try again.");
            }
        }

        return new Account(
                userName,
                password,
                phoneNumber,
                age
        );
    }

    // Collects the login inputs using the same safe text readers.
    private Account readLoginData() {
        String username = readUserName();
        String password = readPassword();
        return new Account(username, password);
    }

    // Prints the menu that would appear after a successful login/signup.
    private void mainProfile() {
        System.out.println("\n========== Main Menu ==========");
        System.out.println("[1] Deposit");
        System.out.println("[2] Withdraw");
        System.out.println("[3] Logout");
    }

    // Wraps signup flow in exception handling so unexpected runtime issues do not terminate the app.
    private void signup() {
        try {
            Account account = readSignupData();
            boolean isAccountCreated = accountService.CreateAccount(account);
            if (isAccountCreated) {
                System.out.println("\nAccount created successfully!");
                mainProfile();
            } else {
                System.out.println("Signup failed: Username already exists or data is invalid.");
            }
        } catch (Exception exception) {
            System.out.println("Signup failed due to an unexpected issue. Please try again.");
        }
    }

    // Wraps login flow in exception handling so invalid data never crashes the application.
    private void login() {
        try {
            Account account = readLoginData();
            boolean isAccountExists = accountService.IsAccountExists(account);
            if (isAccountExists) {
                System.out.println("\nLogin successful!");
                mainProfile();
            } else {
                System.out.println("\nInvalid username or password.");
            }
        } catch (Exception exception) {
            System.out.println("Login failed due to an unexpected issue. Please try again.");
        }
    }

    @Override
    public void start() {
        boolean exist = false;
        int attemptsCounter = 0;
        System.out.println("=================================");
        System.out.println("     Welcome to " + eWalletSystem.getName() + " App     ");
        System.out.println("=================================\n");

        while (true) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("[1] Login");
                System.out.println("[2] Sign Up");
                System.out.println("[3] Exit");
                System.out.print("Enter choice: ");
                String choiceInput = scanner.nextLine().trim();
                int choose;

                try {
                    choose = Integer.parseInt(choiceInput);
                } catch (NumberFormatException exception) {
                    System.out.println("\nInvalid choice. Please enter a valid number.");
                    attemptsCounter++;
                    continue;
                }

                switch (choose) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signup();
                        break;
                    case 3:
                        exist = true;
                        break;
                    default:
                        attemptsCounter++;
                }
            } catch (Exception exception) {
                System.out.println("\nSomething went wrong while processing your request. Please try again.");
                attemptsCounter++;
            }

            if (exist) {
                break;
            }

            if (attemptsCounter == 4) {
                System.out.println("\nToo many invalid attempts.");
                System.out.println("Please contact support.");
                break;
            }
        }
    }
}
