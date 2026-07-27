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

@WebServlet("/wallet")
public class WalletController extends HttpServlet {

    private final AccountService accountService = WalletServiceProvider.getAccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account account = getLoggedInAccount(request, response);
        if (account == null) {
            return;
        }

        String action = request.getParameter("action");

        if ("history".equals(action)) {
            List<Transaction> transactions = accountService.GetAccountTransactionHistory(account.getEmail());
            request.setAttribute("account", account);
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/views/history.jsp").forward(request, response);
            return;
        }

        if ("deposit".equals(action) || "withdraw".equals(action) || "transfer".equals(action)) {
            showOperation(request, response, action, null);
            return;
        }

        response.sendRedirect("dashboard");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account account = getLoggedInAccount(request, response);
        if (account == null) {
            return;
        }

        String action = request.getParameter("action");

        if (!"deposit".equals(action) && !"withdraw".equals(action) && !"transfer".equals(action)) {
            response.sendRedirect("dashboard");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException exception) {
            showOperation(request, response, action, "Amount must be a valid number.");
            return;
        }

        String message;

        if ("deposit".equals(action)) {
            int cardId = getCardId(request);
            message = accountService.GetDepositError(account.getEmail(), cardId, amount);
            if (message == null && accountService.Deposit(account.getEmail(), cardId, amount)) {
                message = "Deposit completed successfully.";
            }
        } else if ("withdraw".equals(action)) {
            int cardId = getCardId(request);
            message = accountService.GetWithdrawError(account.getEmail(), cardId, amount);
            if (message == null && accountService.Withdraw(account.getEmail(), cardId, amount)) {
                message = "Withdraw completed successfully.";
            }
        } else if ("transfer".equals(action)) {
            String recipient = request.getParameter("recipient");
            message = accountService.GetTransferError(account.getEmail(), recipient, amount);
            if (message == null && accountService.Transfer(account.getEmail(), recipient, amount)) {
                message = "Transfer completed successfully.";
            }
        } else {
            message = "Unknown operation.";
        }

        if (message == null) {
            message = "Operation could not be completed.";
        }

        showOperation(request, response, action, message);
    }

    private int getCardId(HttpServletRequest request) {
        try { return Integer.parseInt(request.getParameter("cardId")); }
        catch (NumberFormatException exception) { return 0; }
    }

    private Account getLoggedInAccount(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        String email = SessionData.getLoggedInEmail(session);
        if (email == null) {
            response.sendRedirect("login");
            return null;
        }

        Account account = accountService.GetAccountByEmail(email);
        if (account == null || !account.getIsActive()) {
            session.invalidate();
            response.sendRedirect("login");
            return null;
        }

        return account;
    }

    private void showOperation(HttpServletRequest request, HttpServletResponse response, String action, String message)
            throws ServletException, IOException {

        Account account = getLoggedInAccount(request, response);
        if (account == null) {
            return;
        }

        request.setAttribute("message", message);
        request.setAttribute("account", account);
        request.setAttribute("cards", accountService.GetCards(account.getEmail()));
        request.setAttribute("operation", action);
        request.setAttribute("recipient", request.getParameter("recipient"));
        request.getRequestDispatcher("/views/operation.jsp").forward(request, response);
    }
}
