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

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

    private final AccountService accountService = WalletServiceProvider.getAccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String email = SessionData.getLoggedInEmail(session);
        if (email == null) {
            response.sendRedirect("login");
            return;
        }

        Account currentAccount = accountService.GetAccountByEmail(email);

        if (currentAccount == null || !currentAccount.getIsActive()) {
            session.invalidate();
            response.sendRedirect("login");
            return;
        }

        request.setAttribute("account", currentAccount);
        request.setAttribute("cards", accountService.GetCards(currentAccount.getEmail()));

        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
