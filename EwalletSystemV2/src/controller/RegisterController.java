package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;
import models.OtpIssue;
import models.OtpPurpose;
import services.AccountService;
import services.EmailNotificationService;
import services.OtpVerificationService;
import services.WalletServiceProvider;
import util.SessionData;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private final AccountService accountService = WalletServiceProvider.getAccountService();
    private final EmailNotificationService emailNotificationService = WalletServiceProvider.getEmailNotificationService();
    private final OtpVerificationService otpVerificationService = WalletServiceProvider.getOtpVerificationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        showRegisterPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String ageText = request.getParameter("age");

        int age;

        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException exception) {
            request.setAttribute("message", "Age must be a valid number.");
            showRegisterPage(request, response);
            return;
        }

        Account account = new Account(email, password, phoneNumber, age);
        String error = accountService.GetCreateAccountError(account);

        if (error != null) {
            request.setAttribute("message", error);
            showRegisterPage(request, response);
            return;
        }

        OtpIssue otpIssue = otpVerificationService.issue();
        if (!emailNotificationService.sendOtp(account.getEmail(), otpIssue.getCode(), OtpPurpose.REGISTRATION)) {
            request.setAttribute("message", "We could not send a verification code. Please try again shortly.");
            showRegisterPage(request, response);
            return;
        }

        HttpSession session = request.getSession();
        SessionData.clearLoggedInEmail(session);
        SessionData.savePendingOtp(session, account, OtpPurpose.REGISTRATION, otpIssue);
        response.sendRedirect("verify-otp");
    }

    private void showRegisterPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }
}
