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

@WebServlet("/cards")
public class CardsController extends HttpServlet {
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
        String message;
        if ("add".equals(action)) {
            String[] expiryDate = getExpiryDateParts(request.getParameter("expiryDate"));
            message = accountService.GetAddCardError(account.getEmail(), request.getParameter("bankName"),
                    request.getParameter("cardHolderName"), request.getParameter("cardNumber"),
                    expiryDate[0], expiryDate[1], request.getParameter("cvv"));
            if (message == null && accountService.AddCard(account.getEmail(), request.getParameter("bankName"),
                    request.getParameter("cardHolderName"), request.getParameter("cardNumber"),
                    expiryDate[0], expiryDate[1], request.getParameter("cvv"))) {
                message = "Card added successfully.";
            }
        } else {
            try {
                message = accountService.DeleteCard(account.getEmail(), Integer.parseInt(request.getParameter("cardId")))
                        ? "Card removed successfully." : "Card could not be removed.";
            } catch (NumberFormatException exception) { message = "Invalid card."; }
        }
        showPage(request, response, message);
    }

    private String[] getExpiryDateParts(String expiryDate) {
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return new String[] { "", "" };
        }
        return new String[] { expiryDate.substring(0, 2), "20" + expiryDate.substring(3) };
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        Account account = getAccount(request, response);
        if (account == null) { return; }
        request.setAttribute("account", account);
        request.setAttribute("cards", accountService.GetCards(account.getEmail()));
        request.setAttribute("message", message);
        request.getRequestDispatcher("/views/cards.jsp").forward(request, response);
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
