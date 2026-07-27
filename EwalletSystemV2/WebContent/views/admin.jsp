<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,java.net.URLEncoder,java.nio.charset.StandardCharsets,models.Account,models.Transaction" %>
<%
    Account loggedInAdmin = (Account) request.getAttribute("loggedInAdmin");
    String adminPage = (String) request.getAttribute("adminPage");
    if (adminPage == null) { adminPage = "overview"; }
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    int totalAccounts = request.getAttribute("totalAccounts") == null ? 0 : (Integer) request.getAttribute("totalAccounts");
    int activeAccounts = request.getAttribute("activeAccounts") == null ? 0 : (Integer) request.getAttribute("activeAccounts");
    double totalBalance = request.getAttribute("totalBalance") == null ? 0 : (Double) request.getAttribute("totalBalance");
    String selectedUserEmail = (String) request.getAttribute("selectedUserEmail");
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
    String pageTitle = "overview".equals(adminPage) ? "Control center"
            : "users".equals(adminPage) ? "User management" : "Transaction monitor";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= pageTitle %> - Admin - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=9">
</head>
<body class="admin-body">
    <main class="admin-shell">
        <aside class="admin-sidebar">
            <a class="brand admin-brand" href="${pageContext.request.contextPath}/admin">
                <svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg><span>E-Wallet</span>
            </a>

            <div class="admin-user-card">
                <span class="admin-avatar"><%= loggedInAdmin.getEmail().substring(0, 1).toUpperCase() %></span>
                <div><small>Administrator</small><strong><%= loggedInAdmin.getEmail() %></strong></div>
            </div>

            <nav class="admin-nav" aria-label="Admin navigation">
                <a class="admin-nav-link <%= "overview".equals(adminPage) ? "active" : "" %>" href="${pageContext.request.contextPath}/admin">
                    <span class="admin-nav-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#layout"></use></svg></span>Overview
                </a>
                <a class="admin-nav-link <%= "users".equals(adminPage) ? "active" : "" %>" href="${pageContext.request.contextPath}/admin?page=users">
                    <span class="admin-nav-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#users"></use></svg></span>Users
                </a>
                <a class="admin-nav-link <%= "transactions".equals(adminPage) ? "active" : "" %>" href="${pageContext.request.contextPath}/admin?page=transactions">
                    <span class="admin-nav-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#activity"></use></svg></span>Transactions
                </a>
            </nav>

            <div class="admin-sidebar-footer">
                <button class="admin-signout" type="button" data-dialog-trigger="logoutDialog">Sign out <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg></button>
            </div>
        </aside>

        <section class="admin-content">
            <% if (request.getAttribute("message") != null) { %>
                <p class="alert"><%= request.getAttribute("message") %></p>
            <% } %>

            <% if ("overview".equals(adminPage)) { %>
                <section class="admin-stat-grid" aria-label="Wallet summary">
                    <article class="admin-stat-card"><span class="admin-stat-icon users-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#users"></use></svg></span><p>Total accounts</p><strong><%= totalAccounts %></strong><small><%= activeAccounts %> active accounts</small></article>
                    <article class="admin-stat-card"><span class="admin-stat-icon balance-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#credit-card"></use></svg></span><p>Wallet balances</p><strong>$ <%= String.format("%,.2f", totalBalance) %></strong><small>Across all accounts</small></article>
                </section>

                <section class="admin-overview-grid">
                    <article class="admin-panel admin-recent-panel">
                        <div class="admin-panel-heading"><div><p class="eyebrow">Live feed</p><h2>Recent activity</h2></div><a href="${pageContext.request.contextPath}/admin?page=transactions">View all <svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></a></div>
                        <% if (transactions == null || transactions.isEmpty()) { %>
                            <div class="admin-empty">No wallet activity yet.</div>
                        <% } else { %>
                            <ul class="admin-activity-list compact-list">
                                <% for (int i = 0; i < Math.min(transactions.size(), 5); i++) {
                                    Transaction transaction = transactions.get(i);
                                %>
                                    <li>
                                        <span class="activity-mark <%= transaction.getAmount() > 0 ? "money" : "account" %>"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#<%= transaction.getAmount() > 0 ? "activity" : "user" %>"></use></svg></span>
                                        <div><strong><%= transaction.getTransactionType().replace('_', ' ') %></strong><p><%= transaction.getEmail() %></p></div>
                                        <div class="activity-value"><strong><%= transaction.getAmount() > 0 ? "$ " + transaction.getAmount() : "Account event" %></strong><small><%= transaction.getTransactionDate() %></small></div>
                                    </li>
                                <% } %>
                            </ul>
                        <% } %>
                    </article>

                    <aside class="admin-panel admin-action-panel">
                        <p class="eyebrow">Quick actions</p>
                        <h2>Manage the wallet</h2>
                        <p>Review accounts and investigate money movement without leaving the control center.</p>
                        <a class="admin-quick-link" href="${pageContext.request.contextPath}/admin?page=users"><span><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#users"></use></svg></span><div><strong>Review users</strong><small>Search account status and balances</small></div><b><svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
                        <a class="admin-quick-link" href="${pageContext.request.contextPath}/admin?page=transactions"><span><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#activity"></use></svg></span><div><strong>Monitor transactions</strong><small>Filter financial and account activity</small></div><b><svg class="inline-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#arrow-right"></use></svg></b></a>
                    </aside>
                </section>
            <% } else if ("users".equals(adminPage)) { %>
                <section class="admin-panel admin-table-panel">
                    <div class="admin-table-toolbar">
                        <div><h2>Accounts</h2><p><span data-admin-user-count><%= totalAccounts %></span> records available</p></div>
                        <div class="admin-filters">
                            <label class="admin-search"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#search"></use></svg><input type="search" placeholder="Search email" data-admin-user-search></label>
                            <select data-admin-user-status aria-label="Filter by status"><option value="all">All statuses</option><option value="active">Active</option><option value="inactive">Inactive</option></select>
                            <select data-admin-user-role aria-label="Filter by role"><option value="all">All roles</option><option value="admin">Administrators</option><option value="user">Users</option></select>
                        </div>
                    </div>
                    <% if (accounts == null || accounts.isEmpty()) { %>
                        <div class="admin-empty">No accounts found.</div>
                    <% } else { %>
                        <div class="admin-table-wrap">
                            <table class="admin-table">
                                <thead><tr><th>User</th><th>Contact</th><th>Balance</th><th>Role</th><th>Status</th><th><span class="sr-only">Actions</span></th></tr></thead>
                                <tbody>
                                    <% for (Account account : accounts) { %>
                                        <tr data-admin-user-row data-email="<%= account.getEmail().toLowerCase() %>" data-status="<%= account.getIsActive() ? "active" : "inactive" %>" data-role="<%= account.getIsAdmin() ? "admin" : "user" %>">
                                            <td><div class="admin-table-user"><span><%= account.getEmail().substring(0, 1).toUpperCase() %></span><div><strong><%= account.getEmail() %></strong><small>Age <%= account.getAge() %></small></div></div></td>
                                            <td><%= account.getPhoneNumber() %></td>
                                            <td class="admin-balance">$ <%= String.format("%,.2f", account.getBalance()) %></td>
                                            <td><span class="admin-badge <%= account.getIsAdmin() ? "admin" : "" %>"><%= account.getIsAdmin() ? "Admin" : "User" %></span></td>
                                            <td><span class="admin-badge <%= account.getIsActive() ? "active" : "inactive" %>"><%= account.getIsActive() ? "Active" : "Inactive" %></span></td>
                                            <td class="admin-row-actions">
                                                <% if (!account.getIsAdmin()) { %>
                                                    <a class="admin-icon-action history" href="${pageContext.request.contextPath}/admin?page=transactions&amp;email=<%= URLEncoder.encode(account.getEmail(), StandardCharsets.UTF_8) %>" title="View transaction history"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#clock"></use></svg></a>
                                                    <button class="admin-icon-action edit" type="button" data-admin-edit-trigger data-admin-current-email="<%= account.getEmail() %>" data-admin-email="<%= account.getEmail() %>" data-admin-phone="<%= account.getPhoneNumber() %>" data-admin-age="<%= account.getAge() %>" data-admin-balance="<%= account.getBalance() %>" data-admin-active="<%= account.getIsActive() %>" title="Edit account"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#edit"></use></svg></button>
                                                    <button class="admin-icon-action danger" type="button" data-admin-action-trigger="delete" data-admin-email="<%= account.getEmail() %>" title="Delete account"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#trash"></use></svg></button>
                                                <% } else { %><span class="admin-protected">Protected</span><% } %>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <p class="admin-filter-empty" data-admin-users-empty hidden>No users match the selected filters.</p>
                    <% } %>
                </section>
            <% } else { %>
                <section class="admin-panel admin-transactions-panel">
                    <div class="admin-table-toolbar">
                        <div><h2><%= selectedUserEmail == null || selectedUserEmail.trim().isEmpty() ? "All activity" : "Activity for " + selectedUserEmail %></h2><p><span data-admin-transaction-count><%= transactions == null ? 0 : transactions.size() %></span> events recorded<%= selectedUserEmail == null || selectedUserEmail.trim().isEmpty() ? "" : " · " %><% if (selectedUserEmail != null && !selectedUserEmail.trim().isEmpty()) { %><a class="admin-clear-filter" href="${pageContext.request.contextPath}/admin?page=transactions">Clear user filter</a><% } %></p></div>
                        <div class="admin-filters transaction-filters">
                            <label class="admin-search"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#search"></use></svg><input type="search" placeholder="Search email or description" data-admin-transaction-search></label>
                            <select data-admin-transaction-category aria-label="Filter by category"><option value="all">All activity</option><option value="financial" selected>Financial</option><option value="account">Account log</option></select>
                            <select data-admin-transaction-type aria-label="Filter by type"><option value="all">All types</option><option value="DEPOSIT">Deposit</option><option value="WITHDRAW">Withdraw</option><option value="TRANSFER_SENT">Transfer sent</option><option value="TRANSFER_RECEIVED">Transfer received</option><option value="LOGIN">Login</option><option value="SIGNUP">Signup</option><option value="PASSWORD_CHANGE">Password change</option><option value="ACCOUNT_INACTIVE">Account inactive</option></select>
                        </div>
                    </div>
                    <% if (transactions == null || transactions.isEmpty()) { %>
                        <div class="admin-empty">No transactions found.</div>
                    <% } else { %>
                        <ul class="admin-activity-list admin-transactions-list">
                            <% for (Transaction transaction : transactions) {
                                String type = transaction.getTransactionType();
                                boolean financial = "DEPOSIT".equals(type) || "WITHDRAW".equals(type)
                                        || "TRANSFER_SENT".equals(type) || "TRANSFER_RECEIVED".equals(type);
                                boolean isTransfer = "TRANSFER_SENT".equals(type) || "TRANSFER_RECEIVED".equals(type);
                                String fromEmail = "TRANSFER_SENT".equals(type)
                                        ? transaction.getEmail() : transaction.getRelatedEmail();
                                String toEmail = "TRANSFER_SENT".equals(type)
                                        ? transaction.getRelatedEmail() : transaction.getEmail();
                            %>
                                <li data-admin-transaction-row data-category="<%= financial ? "financial" : "account" %>" data-type="<%= type %>" data-search="<%= (transaction.getEmail() + " " + transaction.getDescription() + " " + (transaction.getRelatedEmail() == null ? "" : transaction.getRelatedEmail())).toLowerCase() %>" <%= financial ? "" : "hidden" %>>
                                    <span class="activity-mark <%= financial ? "money" : "account" %>"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#<%= financial ? "activity" : "user" %>"></use></svg></span>
                                    <div class="activity-content">
                                        <strong><%= type.replace('_', ' ') %></strong>
                                        <p><%= transaction.getDescription() %></p>
                                        <% if (isTransfer) { %>
                                            <small>From: <%= fromEmail %> · To: <%= toEmail %></small>
                                        <% } else { %>
                                            <small><%= transaction.getEmail() %><%= transaction.getRelatedEmail() == null ? "" : " · With: " + transaction.getRelatedEmail() %></small>
                                        <% } %>
                                    </div>
                                    <div class="activity-value"><strong><%= transaction.getAmount() > 0 ? "$ " + String.format("%,.2f", transaction.getAmount()) : "Account event" %></strong><small><%= transaction.getTransactionDate() %></small></div>
                                </li>
                            <% } %>
                        </ul>
                        <p class="admin-filter-empty" data-admin-transactions-empty <%= financialTransactionCount == 0 ? "" : "hidden" %>>No financial transactions found.</p>
                    <% } %>
                </section>
            <% } %>
        </section>
    </main>

    <dialog class="dialog" id="adminEditDialog" aria-labelledby="adminEditTitle">
        <div class="dialog-content admin-edit-dialog">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <p class="eyebrow">User management</p>
            <h2 id="adminEditTitle">Edit account</h2>
            <p class="dialog-copy">Update account details, balance, and access status.</p>
            <form class="admin-edit-form" action="${pageContext.request.contextPath}/admin?page=users" method="post" data-admin-edit-form data-skip-loading-state>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="currentEmail" data-admin-edit-current-email>
                <div class="field"><label for="adminEditEmail">Email address</label><input id="adminEditEmail" name="email" type="email" data-admin-edit-email required></div>
                <div class="field"><label for="adminEditPhone">Phone number</label><input id="adminEditPhone" name="phoneNumber" data-admin-edit-phone required></div>
                <div class="admin-edit-grid">
                    <div class="field"><label for="adminEditAge">Age</label><input id="adminEditAge" name="age" type="number" min="18" data-admin-edit-age required></div>
                    <div class="field"><label for="adminEditBalance">Wallet balance</label><input id="adminEditBalance" name="balance" type="number" min="0" step="0.01" data-admin-edit-balance required></div>
                </div>
                <div class="field"><label for="adminEditStatus">Account status</label><select id="adminEditStatus" name="isActive" data-admin-edit-status><option value="true">Active</option><option value="false">Inactive</option></select></div>
                <div class="dialog-actions"><button class="button button-secondary" type="button" data-dialog-close>Cancel</button><button class="button button-primary" type="submit">Save changes</button></div>
            </form>
        </div>
    </dialog>

    <dialog class="dialog" id="adminEditConfirmDialog" aria-labelledby="adminEditConfirmTitle">
        <div class="dialog-content">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <span class="dialog-icon confirmation-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#check"></use></svg></span>
            <p class="eyebrow">Confirm update</p>
            <h2 id="adminEditConfirmTitle">Save account changes?</h2>
            <p class="dialog-copy">Review the updated customer details before saving them.</p>
            <dl class="confirmation-summary admin-confirmation-summary">
                <div><dt>Email</dt><dd data-admin-confirm-email></dd></div>
                <div><dt>Phone</dt><dd data-admin-confirm-phone></dd></div>
                <div><dt>Age</dt><dd data-admin-confirm-age></dd></div>
                <div><dt>Balance</dt><dd class="confirmation-amount" data-admin-confirm-balance></dd></div>
                <div><dt>Status</dt><dd data-admin-confirm-status></dd></div>
            </dl>
            <div class="dialog-actions"><button class="button button-secondary" type="button" data-dialog-close>Cancel</button><button class="button button-primary" type="button" data-admin-edit-confirm-submit>Save changes</button></div>
        </div>
    </dialog>

    <dialog class="dialog" id="adminActionDialog" aria-labelledby="adminActionTitle">
        <div class="dialog-content">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <span class="dialog-icon danger-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#alert"></use></svg></span>
            <p class="eyebrow" data-admin-action-eyebrow>Confirm action</p>
            <h2 id="adminActionTitle" data-admin-action-title>Manage account</h2>
            <p class="dialog-copy" data-admin-action-copy>This action changes the selected account.</p>
            <form action="${pageContext.request.contextPath}/admin?page=users" method="post">
                <input type="hidden" name="action" data-admin-action-input>
                <input type="hidden" name="email" data-admin-email-input>
                <div class="dialog-actions"><button class="button button-secondary" type="button" data-dialog-close>Cancel</button><button class="button button-danger" type="submit" data-admin-action-submit>Confirm</button></div>
            </form>
        </div>
    </dialog>

    <dialog class="dialog" id="logoutDialog" aria-labelledby="logoutTitle">
        <div class="dialog-content dialog-small">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <span class="dialog-icon danger-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg></span><h2 id="logoutTitle">Sign out?</h2>
            <p>You will need to sign in again to access the control center.</p>
            <div class="dialog-actions"><button class="button button-secondary" type="button" data-dialog-close>Stay signed in</button><a class="button button-danger" href="${pageContext.request.contextPath}/logout">Sign out</a></div>
        </div>
    </dialog>
    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=13"></script>
</body>
</html>
