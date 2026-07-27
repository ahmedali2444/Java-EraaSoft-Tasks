<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,models.Account,models.WalletCard" %>
<%
    Account account = (Account) request.getAttribute("account");
    List<WalletCard> cards = (List<WalletCard>) request.getAttribute("cards");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cards - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/workspace.css?v=7">
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
                <a class="side-link active" href="cards"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#credit-card"></use></svg>Cards</a>
                <a class="side-link" href="wallet?action=history"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#clock"></use></svg>History</a>
                <a class="side-link" href="account"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#user"></use></svg>Account</a>
            </nav>
            <button class="side-link signout" type="button" data-dialog-trigger="logoutDialog"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg>Sign out</button>
        </aside>

        <section class="workspace-content">
            <header class="workspace-header">
                <div>
                    <p class="eyebrow">Payment cards</p>
                    <h1>Manage your cards</h1>
                    <p>Add a card for deposits and withdrawals.</p>
                </div>
            </header>

            <% if (request.getAttribute("message") != null) { %>
                <p class="alert"><%= request.getAttribute("message") %></p>
            <% } %>

            <section class="cards-layout cards-dashboard">
                <form class="panel card-form add-card-panel" action="cards" method="post" autocomplete="off">
                    <input type="hidden" name="action" value="add">
                    <div class="form-heading">
                        <span class="form-heading-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#plus"></use></svg></span>
                        <div>
                            <h2>Add a card</h2>
                            <p>Use a saved card for deposits and withdrawals.</p>
                        </div>
                    </div>

                    <div class="form-stack">
                        <div class="field">
                            <label>Bank name</label>
                            <input name="bankName" placeholder="Bank name" autocomplete="off" required>
                        </div>
                        <div class="field">
                            <label>Card holder name</label>
                            <input name="cardHolderName" placeholder="Name on card" autocomplete="off" required>
                        </div>
                        <div class="field">
                            <label>Card number</label>
                            <input name="cardNumber" maxlength="19" inputmode="numeric" placeholder="0000 0000 0000 0000" pattern="[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}" title="Enter exactly 16 digits." autocomplete="off" required>
                        </div>
                        <div class="form-row card-meta-row">
                            <div class="field">
                                <label>Expiry date</label>
                                <input name="expiryDate" maxlength="5" inputmode="numeric" placeholder="02/26" pattern="(0[1-9]|1[0-2])/[0-9]{2}" title="Use the format MM/YY, for example 02/26." autocomplete="off" required>
                            </div>
                            <div class="field">
                                <label>CVV</label>
                                <input name="cvv" type="password" maxlength="3" inputmode="numeric" placeholder="123" autocomplete="off" required>
                            </div>
                        </div>
                        <button class="full-button" type="submit">Save card</button>
                    </div>
                </form>

                <section class="saved-cards cards-list-panel">
                    <% if (cards == null || cards.isEmpty()) { %>
                        <div class="empty-state">No cards added yet. Add your first card to make deposits or withdrawals.</div>
                    <% } else { for (WalletCard card : cards) { %>
                        <article class="payment-card">
                            <span><%= card.getBankName() %></span>
                            <strong>&bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; <%= card.getLastFourDigits() %></strong>
                            <p><%= card.getCardHolderName() %></p>
                            <small>Expires <%= String.format("%02d", card.getExpiryMonth()) %>/<%= card.getExpiryYear() %></small>
                            <form action="cards" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="cardId" value="<%= card.getCardId() %>">
                                <button class="card-remove" type="submit">Remove</button>
                            </form>
                        </article>
                    <% } } %>
                </section>
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
    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=10"></script>
</body>
</html>
