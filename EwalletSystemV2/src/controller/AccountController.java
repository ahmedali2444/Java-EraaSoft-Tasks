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
import services.WalletServiceProvider;
import util.SessionData;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private final AccountService accountService = WalletServiceProvider.getAccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showPage(request, response, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = getAccount(request, response);
        if (account == null) { return; }
        String action = request.getParameter("action");
        String message = "Unknown operation.";
        if ("profile".equals(action)) {
            int age;
            try { age = Integer.parseInt(request.getParameter("age")); }
            catch (NumberFormatException exception) { showPage(request, response, "Age must be a valid number.", "editProfile"); return; }
            message = accountService.GetUpdateProfileError(account.getEmail(), request.getParameter("phoneNumber"), age);
            if (message == null && accountService.UpdateProfile(account.getEmail(), request.getParameter("phoneNumber"), age)) {
                message = "Account details updated successfully.";
            }
        } else if ("password".equals(action)) {
            String newPassword = request.getParameter("newPassword");
            if (newPassword == null || !newPassword.equals(request.getParameter("confirmPassword"))) {
                showPage(request, response, "New password and confirmation do not match.", "changePassword");
                return;
            }
            message = accountService.GetChangePasswordError(account.getEmail(), request.getParameter("oldPassword"), request.getParameter("newPassword"));
            if (message == null && accountService.ChangePassword(account.getEmail(), request.getParameter("oldPassword"), request.getParameter("newPassword"))) {
                message = "Password changed successfully.";
            }
        }
        boolean successful = "Account details updated successfully.".equals(message)
                || "Password changed successfully.".equals(message);
        showPage(request, response, message == null ? "Operation could not be completed." : message,
                successful ? null : ("profile".equals(action) ? "editProfile" : "changePassword"));
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        showPage(request, response, message, null);
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response, String message, String openModal) throws ServletException, IOException {
        Account account = getAccount(request, response);
        if (account == null) { return; }
        request.setAttribute("message", message);
        request.setAttribute("openModal", openModal);
        request.setAttribute("account", account);
        request.getRequestDispatcher("/views/account.jsp").forward(request, response);
    }

    private Account getAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String email = SessionData.getLoggedInEmail(session);
        if (email == null) { response.sendRedirect("login"); return null; }
        Account account = accountService.GetAccountByEmail(email);
        if (account == null || !account.getIsActive()) { session.invalidate(); response.sendRedirect("login"); return null; }
        return account;
    }
}
