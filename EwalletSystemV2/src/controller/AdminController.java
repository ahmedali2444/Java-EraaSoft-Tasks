package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;
import models.Transaction;
import services.AccountService;
import services.WalletServiceProvider;
import util.SessionData;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

    private final AccountService accountService = WalletServiceProvider.getAccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isAdmin(request, response)) {
            return;
        }

        showAdminPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isAdmin(request, response)) {
            return;
        }

        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String message;

        if ("update".equals(action)) {
            message = updateAccount(request);
        } else if ("delete".equals(action)) {
            message = accountService.GetDeleteAccountError(email);
            if (message == null && accountService.DeleteAccount(email)) {
                message = "Account deleted successfully.";
            }
        } else if ("inactive".equals(action)) {
            message = accountService.GetInactiveAccountError(email);
            if (message == null && accountService.InactiveAccount(email)) {
                message = "Account is inactive now.";
            }
        } else {
            message = "Unknown operation.";
        }

        if (message == null) {
            message = "Operation could not be completed.";
        }

        request.setAttribute("message", message);
        showAdminPage(request, response);
    }

    private String updateAccount(HttpServletRequest request) {
        int age;
        double balance;
        try {
            age = Integer.parseInt(request.getParameter("age"));
            balance = Double.parseDouble(request.getParameter("balance"));
        } catch (NumberFormatException exception) {
            return "Age and balance must be valid numbers.";
        }

        String currentEmail = request.getParameter("currentEmail");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        boolean isActive = "true".equals(request.getParameter("isActive"));
        String error = accountService.GetAdminUpdateAccountError(currentEmail, email, phoneNumber, age, balance, isActive);
        if (error != null) {
            return error;
        }
        return accountService.UpdateAccountByAdmin(currentEmail, email, phoneNumber, age, balance, isActive)
                ? "Account updated successfully." : "Account could not be updated.";
    }

    private boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String email = SessionData.getLoggedInEmail(session);
        if (email == null) {
            response.sendRedirect("login");
            return false;
        }

        Account account = accountService.GetAccountByEmail(email);
        if (account == null || !account.getIsActive() || !account.getIsAdmin()) {
            session.invalidate();
            response.sendRedirect("login");
            return false;
        }

        request.setAttribute("loggedInAdmin", account);
        return true;
    }

    private void showAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = request.getParameter("page");
        if (!"users".equals(page) && !"transactions".equals(page)) {
            page = "overview";
        }

        List<Account> accounts = accountService.GetAllAccounts();
        List<Transaction> allTransactions = accountService.GetTransactionHistory();
        String selectedUserEmail = request.getParameter("email");
        List<Transaction> transactions = "transactions".equals(page) && selectedUserEmail != null
                && !selectedUserEmail.trim().isEmpty()
                ? accountService.GetAccountTransactionHistory(selectedUserEmail) : allTransactions;
        int activeAccounts = 0;
        double totalBalance = 0;

        for (Account account : accounts) {
            if (account.getIsActive()) {
                activeAccounts++;
            }
            totalBalance += account.getBalance();
        }

        request.setAttribute("adminPage", page);
        request.setAttribute("accounts", accounts);
        request.setAttribute("transactions", transactions);
        request.setAttribute("selectedUserEmail", selectedUserEmail);
        request.setAttribute("totalAccounts", accounts.size());
        request.setAttribute("activeAccounts", activeAccounts);
        request.setAttribute("totalBalance", totalBalance);

        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
    }
}
