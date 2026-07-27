<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,models.Account,models.WalletCard" %>
<%
    Account account = (Account) request.getAttribute("account");
    List<WalletCard> cards = (List<WalletCard>) request.getAttribute("cards");
    int cardCount = cards == null ? 0 : cards.size();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Overview - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/overview.css?v=5">
</head>
<body class="overview-body">
    <main class="overview-page">
        <header class="overview-topbar">
            <a class="brand" href="dashboard"><svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg><span>E-Wallet</span></a>
            <div class="overview-actions">
                <a class="icon-button" href="account" aria-label="Open account" title="Account">
                    <svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#user"></use></svg>
                </a>
                <button class="icon-button logout-button" type="button" data-dialog-trigger="logoutDialog" aria-label="Sign out" title="Sign out">
                    <svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg>
                </button>
            </div>
        </header>

        <section class="overview-hero">
            <p class="eyebrow">Wallet overview</p>
            <h1>Good to see you</h1>
            <p>Everything you need for your money, in one clear place.</p>
        </section>

        <section class="overview-grid">
            <article class="balance-card">
                <p>Available balance</p>
                <strong>$ <%= account.getBalance() %></strong>
                <span>Use your balance to transfer money instantly.</span>
            </article>
            <a class="quick-card card-link" href="cards">
                <span>Linked cards</span>
                <strong><%= cardCount %></strong>
                <small><%= cardCount == 0 ? "Add a card to get started" : "Manage your cards" %> <b><svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></small>
            </a>
        </section>

        <section class="action-shortcuts" aria-label="Wallet actions">
            <a class="action-shortcut" href="wallet?action=deposit"><span class="shortcut-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-down"></use></svg></span><strong>Deposit</strong><small>Add money from a saved card</small><b>Open <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
            <a class="action-shortcut" href="wallet?action=withdraw"><span class="shortcut-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-up"></use></svg></span><strong>Withdraw</strong><small>Move money to a saved card</small><b>Open <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
            <a class="action-shortcut" href="wallet?action=transfer"><span class="shortcut-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#send"></use></svg></span><strong>Transfer</strong><small>Send money to another wallet</small><b>Open <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
            <a class="action-shortcut" href="cards"><span class="shortcut-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#credit-card"></use></svg></span><strong>Cards</strong><small><%= cardCount == 0 ? "Add your first card" : "View and manage cards" %></small><b>Open <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
            <a class="action-shortcut" href="wallet?action=history"><span class="shortcut-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#clock"></use></svg></span><strong>Transaction History</strong><small>Review your financial activity</small><b>Open <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
        </section>
    </main>

    <dialog class="dialog" id="logoutDialog" aria-labelledby="logoutTitle">
        <div class="dialog-content dialog-small">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <span class="dialog-icon danger-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg></span>
            <h2 id="logoutTitle">Sign out?</h2>
            <p>You will need to sign in again to access your wallet.</p>
            <div class="dialog-actions"><button class="button button-secondary" type="button" data-dialog-close>Stay signed in</button><a class="button button-danger" href="logout">Sign out</a></div>
        </div>
    </dialog>
    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=10"></script>
</body>
</html>
