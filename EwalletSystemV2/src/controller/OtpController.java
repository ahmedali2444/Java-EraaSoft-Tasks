package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;
import models.OtpChallenge;
import models.OtpIssue;
import models.OtpPurpose;
import models.OtpVerificationStatus;
import services.AccountService;
import services.EmailNotificationService;
import services.OtpVerificationService;
import services.WalletServiceProvider;
import util.SessionData;

@WebServlet("/verify-otp")
public class OtpController extends HttpServlet {

    private final AccountService accountService = WalletServiceProvider.getAccountService();
    private final EmailNotificationService emailNotificationService = WalletServiceProvider.getEmailNotificationService();
    private final OtpVerificationService otpVerificationService = WalletServiceProvider.getOtpVerificationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Account account = SessionData.getPendingAccount(session);
        OtpPurpose purpose = SessionData.getPendingPurpose(session);
        if (account == null || purpose == null || SessionData.getPendingChallenge(session) == null) {
            response.sendRedirect("login");
            return;
        }

        showOtpPage(request, response, account, purpose, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Account account = SessionData.getPendingAccount(session);
        OtpPurpose purpose = SessionData.getPendingPurpose(session);
        OtpChallenge challenge = SessionData.getPendingChallenge(session);
        if (session == null || account == null || purpose == null || challenge == null) {
            response.sendRedirect("login");
            return;
        }

        if ("resend".equals(request.getParameter("action"))) {
            resendCode(request, response, session, account, purpose, challenge);
            return;
        }

        String code = request.getParameter("otp");
        if (code != null) {
            code = code.replaceAll("\\D", "");
        }
        OtpVerificationStatus status = otpVerificationService.verify(challenge, code);
        if (status != OtpVerificationStatus.VERIFIED) {
            SessionData.saveOtpChallenge(session, challenge);
            showOtpPage(request, response, account, purpose, getVerificationMessage(status));
            return;
        }

        if (purpose == OtpPurpose.LOGIN) {
            completeLogin(request, response, account);
        } else {
            completeRegistration(request, response, account);
        }
    }

    private void resendCode(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            Account account, OtpPurpose purpose, OtpChallenge challenge) throws ServletException, IOException {

        if (!challenge.canResend()) {
            showOtpPage(request, response, account, purpose, "Please wait 30 seconds before requesting another code.");
            return;
        }

        OtpIssue otpIssue = otpVerificationService.issue();
        if (!emailNotificationService.sendOtp(account.getEmail(), otpIssue.getCode(), purpose)) {
            showOtpPage(request, response, account, purpose, "We could not send a new code. Please try again shortly.");
            return;
        }

        SessionData.saveOtpChallenge(session, otpIssue.getChallenge());
        showOtpPage(request, response, account, purpose, "A new code has been sent to your email.");
    }

    private void completeLogin(HttpServletRequest request, HttpServletResponse response, Account pendingAccount)
            throws IOException {

        Account account = accountService.GetAccountByEmail(pendingAccount.getEmail());
        if (account == null || !account.getIsActive() || account.getIsAdmin()) {
            clearPendingOtp(request);
            response.sendRedirect("login");
            return;
        }

        HttpSession session = request.getSession();
        SessionData.clearPendingOtp(session);
        SessionData.setLoggedInEmail(session, account.getEmail());
        accountService.AddTransaction(account.getEmail(), "LOGIN", 0, null, "Login completed successfully.");
        response.sendRedirect("dashboard");
    }

    private void completeRegistration(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

        String validationError = accountService.GetCreateAccountError(account);
        if (validationError != null || !accountService.CreateAccount(account)) {
            clearPendingOtp(request);
            request.setAttribute("message", validationError == null
                    ? "Account could not be created. Please try again." : validationError);
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        Account createdAccount = accountService.GetAccountByEmail(account.getEmail());
        HttpSession session = request.getSession();
        SessionData.clearPendingOtp(session);
        SessionData.setLoggedInEmail(session, createdAccount.getEmail());
        response.sendRedirect("dashboard");
    }

    private void clearPendingOtp(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            SessionData.clearPendingOtp(session);
        }
    }

    private void showOtpPage(HttpServletRequest request, HttpServletResponse response, Account account,
            OtpPurpose purpose, String message) throws ServletException, IOException {

        request.setAttribute("otpEmail", maskEmail(account.getEmail()));
        request.setAttribute("otpContext", purpose == OtpPurpose.LOGIN ? "sign in" : "create your account");
        request.setAttribute("otpMessage", message);
        request.getRequestDispatcher("/views/otp.jsp").forward(request, response);
    }

    private String getVerificationMessage(OtpVerificationStatus status) {
        if (status == OtpVerificationStatus.EXPIRED) {
            return "This code has expired. Request a new one to continue.";
        }
        if (status == OtpVerificationStatus.LOCKED) {
            return "Too many incorrect attempts. Request a new code to continue.";
        }
        return "Enter the 6-digit code from your email.";
    }

    private String maskEmail(String email) {
        int separator = email == null ? -1 : email.indexOf('@');
        if (separator <= 1) {
            return email;
        }
        return email.substring(0, 1) + "â€¢â€¢â€¢" + email.substring(separator - 1);
    }
}
