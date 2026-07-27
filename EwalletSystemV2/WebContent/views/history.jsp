<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,java.net.URLEncoder,java.nio.charset.StandardCharsets,models.Account,models.Transaction" %>
<%
    Account account = (Account) request.getAttribute("account");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    int financialTransactionCount = 0;
    if (transactions != null) {
        for (Transaction transaction : transactions) {
            String type = transaction.getTransactionType();
            if ("DEPOSIT".equals(type) || "WITHDRAW".equals(type)
                    || "TRANSFER_SENT".equals(type) || "TRANSFER_RECEIVED".equals(type)) {
                financialTransactionCount++;
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>History - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=6">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/workspace.css?v=8">
</head>
<body>
    <main class="workspace">
        <aside class="side-panel">
            <a class="brand" href="dashboard"><svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg><span>E-Wallet</span></a>
            <div class="side-user"><span>Signed in as</span><strong><%= account.getEmail() %></strong></div>
            <nav class="side-nav">
                <a class="side-link" href="dashboard"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#layout"></use></svg>Overview</a>
                <a class="side-link" href="wallet?action=deposit"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-down"></use></svg>Deposit</a>
                <a class="side-link" href="wallet?action=withdraw"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-up"></use></svg>Withdraw</a>
                <a class="side-link" href="wallet?action=transfer"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#send"></use></svg>Transfer</a>
                <a class="side-link" href="cards"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#credit-card"></use></svg>Cards</a>
                <a class="side-link active" href="wallet?action=history"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#clock"></use></svg>History</a>
                <a class="side-link" href="account"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#user"></use></svg>Account</a>
            </nav>
            <button class="side-link signout" type="button" data-dialog-trigger="logoutDialog"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg>Sign out</button>
        </aside>

        <section class="workspace-content">
            <header class="workspace-header">
                <div>
                    <p class="eyebrow">Wallet activity</p>
                    <h1>Transaction history</h1>
                    <p>Every completed wallet activity appears here.</p>
                </div>
            </header>

            <section class="panel page-panel history-panel">
                <% if (transactions == null || transactions.isEmpty()) { %>
                    <div class="empty-state">No transactions found yet.</div>
                <% } else { %>
                    <div class="history-toolbar">
                        <span>Show</span>
                        <div class="history-filter" role="group" aria-label="Filter activity history">
                            <button class="history-filter-button" type="button" data-history-filter="all">All activity</button>
                            <button class="history-filter-button active" type="button" data-history-filter="financial">Financial</button>
                            <button class="history-filter-button" type="button" data-history-filter="account">Account log</button>
                        </div>
                    </div>
                    <ul class="timeline">
                        <% for (Transaction transaction : transactions) {
                            String type = transaction.getTransactionType();
                            boolean isFinancial = "DEPOSIT".equals(type) || "WITHDRAW".equals(type)
                                    || "TRANSFER_SENT".equals(type) || "TRANSFER_RECEIVED".equals(type);
                            boolean isTransfer = "TRANSFER_SENT".equals(type) || "TRANSFER_RECEIVED".equals(type);
                            String fromEmail = "TRANSFER_SENT".equals(type)
                                    ? transaction.getEmail() : transaction.getRelatedEmail();
                            String toEmail = "TRANSFER_SENT".equals(type)
                                    ? transaction.getRelatedEmail() : transaction.getEmail();
                            String transferRecipient = isTransfer ? transaction.getRelatedEmail() : null;
                        %>
                            <li class="<%= isTransfer ? "has-transfer-action" : "" %>" data-history-category="<%= isFinancial ? "financial" : "account" %>" <%= isFinancial ? "" : "hidden" %>>
                                <strong><%= transaction.getTransactionType().replace('_', ' ') %></strong>
                                <span class="transaction-meta"><%= transaction.getAmount() > 0 ? "$ " + transaction.getAmount() : "" %></span>
                                <% if (isTransfer) { %>
                                    <span class="transaction-meta">From: <%= fromEmail %> · To: <%= toEmail %></span>
                                <% } else if (transaction.getRelatedEmail() != null) { %>
                                    <span class="transaction-meta">With: <%= transaction.getRelatedEmail() %></span>
                                <% } %>
                                <p><%= transaction.getDescription() %></p>
                                <small><%= transaction.getTransactionDate() %></small>
                                <% if (transferRecipient != null && !transferRecipient.trim().isEmpty()) { %>
                                    <a class="transfer-repeat-link" href="wallet?action=transfer&amp;recipient=<%= URLEncoder.encode(transferRecipient, StandardCharsets.UTF_8) %>">Send money <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#send"></use></svg></a>
                                <% } %>
                            </li>
                        <% } %>
                    </ul>
                    <p class="history-filter-empty" <%= financialTransactionCount == 0 ? "" : "hidden" %>>No activity in this category yet.</p>
                <% } %>
            </section>
        </section>
    </main>

    <dialog class="dialog" id="logoutDialog" aria-labelledby="logoutTitle">
        <div class="dialog-content dialog-small">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <span class="dialog-icon danger-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg></span>
            <h2 id="logoutTitle">Sign out?</h2>
            <p>You will need to sign in again to access your wallet.</p>
            <div class="dialog-actions">
                <button class="button button-secondary" type="button" data-dialog-close>Stay signed in</button>
                <a class="button button-danger" href="logout">Sign out</a>
            </div>
        </div>
    </dialog>
    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=14"></script>
</body>
</html>
