<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Account" %>
<%
    Account account = (Account) request.getAttribute("account");
    String openModal = (String) request.getAttribute("openModal");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/workspace.css?v=7">
</head>
<body <%= openModal == null ? "" : "data-open-dialog=\"" + openModal + "\"" %>>
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
                <a class="side-link" href="wallet?action=history"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#clock"></use></svg>History</a>
                <a class="side-link active" href="account"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#user"></use></svg>Account</a>
            </nav>
            <button class="side-link signout" type="button" data-dialog-trigger="logoutDialog"><svg class="nav-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logout"></use></svg>Sign out</button>
        </aside>

        <section class="workspace-content">
            <header class="workspace-header">
                <div>
                    <p class="eyebrow">Account settings</p>
                    <h1>Your account</h1>
                    <p>Keep your profile and access details up to date.</p>
                </div>
            </header>

            <% if (request.getAttribute("message") != null) { %>
                <p class="alert"><%= request.getAttribute("message") %></p>
            <% } %>

            <section class="account-profile panel">
                <div class="account-avatar"><%= account.getEmail().substring(0, 1).toUpperCase() %></div>
                <div>
                    <p class="card-label">Wallet owner</p>
                    <h2><%= account.getEmail() %></h2>
                    <span>Account active</span>
                </div>
                <div class="account-profile-actions">
                    <button class="button button-secondary" type="button" data-dialog-trigger="editProfile">Edit details</button>
                    <button class="button" type="button" data-dialog-trigger="changePassword">Change password</button>
                </div>
            </section>

            <section class="account-details">
                <article><span>Email address</span><strong><%= account.getEmail() %></strong></article>
                <article><span>Phone number</span><strong><%= account.getPhoneNumber() %></strong></article>
                <article><span>Age</span><strong><%= account.getAge() %> years</strong></article>
                <article><span>Current balance</span><strong class="green-value">$ <%= account.getBalance() %></strong></article>
            </section>
        </section>
    </main>

    <dialog class="dialog" id="editProfile" aria-labelledby="editProfileTitle">
        <div class="dialog-content">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <p class="eyebrow">Profile details</p>
            <h2 id="editProfileTitle">Edit your information</h2>
            <p class="dialog-copy">Your email address is tied to your wallet and cannot be changed here.</p>
            <form class="form-stack" action="account" method="post">
                <input type="hidden" name="action" value="profile">
                <div class="field"><label>Email address</label><input value="<%= account.getEmail() %>" disabled></div>
                <div class="field"><label>Phone number</label><input name="phoneNumber" value="<%= account.getPhoneNumber() %>" required></div>
                <div class="field"><label>Age</label><input name="age" type="number" value="<%= account.getAge() %>" required></div>
                <div class="dialog-actions">
                    <button class="button button-secondary" type="button" data-dialog-close>Cancel</button>
                    <button type="submit">Save changes</button>
                </div>
            </form>
        </div>
    </dialog>

    <dialog class="dialog" id="changePassword" aria-labelledby="changePasswordTitle">
        <div class="dialog-content">
            <button class="dialog-close" type="button" data-dialog-close aria-label="Close"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#x"></use></svg></button>
            <p class="eyebrow">Security</p>
            <h2 id="changePasswordTitle">Choose a new password</h2>
            <p class="dialog-copy">Use a strong password you do not use elsewhere.</p>
            <form class="form-stack" action="account" method="post">
                <input type="hidden" name="action" value="password">
                <div class="field"><label>Current password</label><input name="oldPassword" type="password" required></div>
                <div class="field"><label>New password</label><input name="newPassword" type="password" minlength="8" required></div>
                <div class="field"><label>Confirm new password</label><input name="confirmPassword" type="password" minlength="8" required></div>
                <div class="dialog-actions">
                    <button class="button button-secondary" type="button" data-dialog-close>Cancel</button>
                    <button type="submit">Update password</button>
                </div>
            </form>
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
    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=10"></script>
</body>
</html>
