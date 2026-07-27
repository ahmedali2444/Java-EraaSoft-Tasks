package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;
import services.AccountService;
import services.EmailNotificationService;
import services.OtpVerificationService;
import services.WalletServiceProvider;
import models.OtpIssue;
import models.OtpPurpose;
import util.SessionData;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final AccountService accountService = WalletServiceProvider.getAccountService();
    private final EmailNotificationService emailNotificationService = WalletServiceProvider.getEmailNotificationService();
    private final OtpVerificationService otpVerificationService = WalletServiceProvider.getOtpVerificationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        showLoginPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = new Account(email, password);

        if (!accountService.IsEmailExists(email)) {
            request.setAttribute("message", "Email does not exist.");
            showLoginPage(request, response);
            return;
        }

        if (!accountService.IsAccountActive(email)) {
            request.setAttribute("message", "This account is inactive.");
            showLoginPage(request, response);
            return;
        }

        if (!accountService.IsPasswordMatches(account)) {
            request.setAttribute("message", "Incorrect password.");
            showLoginPage(request, response);
            return;
        }

        Account loggedInAccount = accountService.GetAccountByEmail(email);
        HttpSession session = request.getSession();

        if (loggedInAccount.getIsAdmin()) {
            accountService.AddTransaction(email, "LOGIN", 0, null, "Login completed successfully.");
            SessionData.setLoggedInEmail(session, loggedInAccount.getEmail());
            response.sendRedirect("admin");
            return;
        }

        OtpIssue otpIssue = otpVerificationService.issue();
        if (!emailNotificationService.sendOtp(loggedInAccount.getEmail(), otpIssue.getCode(), OtpPurpose.LOGIN)) {
            request.setAttribute("message", "We could not send a verification code. Please try again shortly.");
            showLoginPage(request, response);
            return;
        }

        SessionData.clearLoggedInEmail(session);
        SessionData.savePendingOtp(session, loggedInAccount, OtpPurpose.LOGIN, otpIssue);
        response.sendRedirect("verify-otp");
    }

    private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
