<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,models.Account,models.WalletCard" %>
<%!
    private String escapeAttribute(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
%>
<%
    Account account = (Account) request.getAttribute("account");
    List<WalletCard> cards = (List<WalletCard>) request.getAttribute("cards");
    String operation = (String) request.getAttribute("operation");
    String recipient = (String) request.getAttribute("recipient");
    boolean needsCard = "deposit".equals(operation) || "withdraw".equals(operation);
    boolean hasCards = cards != null && !cards.isEmpty();
    String title = "deposit".equals(operation) ? "Deposit money" : "withdraw".equals(operation) ? "Withdraw money" : "Send a transfer";
    String description = "deposit".equals(operation) ? "Add money to your wallet from one of your saved cards." : "withdraw".equals(operation) ? "Move funds from your wallet to a saved card." : "Send money securely to another E-Wallet user.";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= title %> - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=6">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/workspace.css?v=7">
</head>
<body>
    <main class="workspace">
        <aside class="side-panel">
            <a class="brand" href="dashboard"><svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg><span>E-Wallet</span></a>
            <div class="side-user"><span>Signed in as</span><strong><%= account.getEmail() %></strong></div>
            <nav class="side-nav">
                <a class="side-link" href="dashboard"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#layout"></use></svg>Overview</a>
                <a class="side-link <%= "deposit".equals(operation) ? "active" : "" %>" href="wallet?action=deposit"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-down"></use></svg>Deposit</a>
                <a class="side-link <%= "withdraw".equals(operation) ? "active" : "" %>" href="wallet?action=withdraw"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-up"></use></svg>Withdraw</a>
                <a class="side-link <%= "transfer".equals(operation) ? "active" : "" %>" href="wallet?action=transfer"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#send"></use></svg>Transfer</a>
                <a class="side-link" href="cards"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#credit-card"></use></svg>Cards</a>
                <a class="side-link" href="wallet?action=history"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#clock"></use></svg>History</a>
                <a class="side-link" href="account"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#user"></use></svg>Account</a>
            </nav>
            <button class="side-link signout" type="button" data-dialog-trigger="logoutDialog"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg>Sign out</button>
        </aside>

        <section class="workspace-content operation-page">
            <a class="back-link" href="dashboard"><svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-left"></use></svg> Back to overview</a>
            <header class="workspace-header">
                <div>
                    <p class="eyebrow"><%= operation %></p>
                    <h1><%= title %></h1>
                    <p><%= description %></p>
                </div>
            </header>

            <% if (request.getAttribute("message") != null) { %>
                <p class="alert"><%= request.getAttribute("message") %></p>
            <% } %>

            <% if (needsCard && !hasCards) { %>
                <section class="operation-empty panel">
                    <span class="empty-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#credit-card"></use></svg></span>
                    <h2>Add a card first</h2>
                    <p>To <%= operation %> money, connect a payment card to your wallet first.</p>
                    <a class="button button-primary" href="cards">Add a card</a>
                </section>
            <% } else { %>
                <form class="panel operation-form" action="wallet" method="post" data-operation-confirm-form data-skip-loading-state>
                    <input type="hidden" name="action" value="<%= operation %>">
                    <% if (needsCard) { %>
                        <div class="field">
                            <label>Select a card</label>
                            <select name="cardId" required>
                                <option value="">Choose a saved card</option>
                                <% for (WalletCard card : cards) { %>
                                    <option value="<%= card.getCardId() %>"><%= card.getBankName() %> &bull;&bull;&bull;&bull; <%= card.getLastFourDigits() %></option>
                                <% } %>
                            </select>
                        </div>
                    <% } %>
                    <% if ("transfer".equals(operation)) { %>
                        <div class="field">
                            <label>Recipient email or phone</label>
                            <input type="text" name="recipient" value="<%= escapeAttribute(recipient) %>" placeholder="name@example.com or +2010..." required>
                        </div>
                    <% } %>
                    <div class="field">
                        <label>Amount</label>
                        <div class="amount-input"><span>$</span><input type="number" name="amount" min="100" step="100" placeholder="100" required></div>
                        <small>Minimum amount is $100, in increments of $100.</small>
                    </div>
                    <button type="submit"><%= "transfer".equals(operation) ? "Send transfer" : "deposit".equals(operation) ? "Confirm deposit" : "Confirm withdrawal" %></button>
                </form>
            <% } %>
        </section>
    </main>

    <dialog class="dialog" id="operationConfirmDialog" aria-labelledby="operationConfirmTitle">
        <div class="dialog-content">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <span class="dialog-icon confirmation-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#check"></use></svg></span>
            <p class="eyebrow">Review operation</p>
            <h2 id="operationConfirmTitle" data-operation-confirm-title>Confirm operation</h2>
            <p class="dialog-copy">Please review the details before moving money.</p>
            <dl class="confirmation-summary">
                <div><dt>Operation</dt><dd data-operation-confirm-action></dd></div>
                <div><dt>Amount</dt><dd class="confirmation-amount" data-operation-confirm-amount></dd></div>
                <div data-operation-confirm-card-row><dt>Card</dt><dd data-operation-confirm-card></dd></div>
                <div data-operation-confirm-recipient-row hidden><dt>Recipient</dt><dd data-operation-confirm-recipient></dd></div>
            </dl>
            <div class="dialog-actions"><button class="button button-secondary" type="button" data-dialog-close>Cancel</button><button class="button button-primary" type="button" data-operation-confirm-submit>Confirm</button></div>
        </div>
    </dialog>

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
