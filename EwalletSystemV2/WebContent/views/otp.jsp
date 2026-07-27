<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify your email - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/otp.css?v=1">
</head>
<body class="otp-page">
    <header class="site-header otp-header">
        <a class="brand" href="${pageContext.request.contextPath}/home">
            <svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg>
            <span>E-Wallet</span>
        </a>
        <a class="header-link" href="${pageContext.request.contextPath}/login">Back to sign in</a>
    </header>

    <main class="otp-layout">
        <section class="otp-card" aria-labelledby="otpTitle">
            <div class="otp-icon" aria-hidden="true">
                <svg class="ui-icon" viewBox="0 0 24 24"><use href="${pageContext.request.contextPath}/assets/icons.svg?v=2#mail"></use></svg>
            </div>
            <p class="eyebrow">Secure verification</p>
            <h1 id="otpTitle">Check your email</h1>
            <p class="otp-copy">Enter the 6-digit code we sent to <strong>${otpEmail}</strong> to ${otpContext}.</p>

            <% if (request.getAttribute("otpMessage") != null) { %>
                <p class="otp-message" role="status"><%= request.getAttribute("otpMessage") %></p>
            <% } %>

            <form class="otp-form" action="${pageContext.request.contextPath}/verify-otp" method="post" data-otp-form data-skip-loading-state novalidate>
                <input type="hidden" name="otp" data-otp-value>
                <div class="otp-inputs" role="group" aria-label="6-digit verification code">
                    <div class="otp-input-group">
                        <input class="otp-digit" type="text" inputmode="numeric" autocomplete="one-time-code" maxlength="1" aria-label="Digit 1" data-otp-input autofocus>
                        <input class="otp-digit" type="text" inputmode="numeric" maxlength="1" aria-label="Digit 2" data-otp-input>
                        <input class="otp-digit" type="text" inputmode="numeric" maxlength="1" aria-label="Digit 3" data-otp-input>
                    </div>
                    <span class="otp-separator" aria-hidden="true"></span>
                    <div class="otp-input-group">
                        <input class="otp-digit" type="text" inputmode="numeric" maxlength="1" aria-label="Digit 4" data-otp-input>
                        <input class="otp-digit" type="text" inputmode="numeric" maxlength="1" aria-label="Digit 5" data-otp-input>
                        <input class="otp-digit" type="text" inputmode="numeric" maxlength="1" aria-label="Digit 6" data-otp-input>
                    </div>
                </div>
                <p class="otp-input-error" data-otp-error aria-live="polite"></p>
                <button class="otp-submit" type="submit">Verify code</button>
            </form>

            <div class="otp-footer">
                <p>Code expires in 10 minutes.</p>
                <form action="${pageContext.request.contextPath}/verify-otp" method="post" data-skip-loading-state>
                    <input type="hidden" name="action" value="resend">
                    <button class="otp-resend" type="submit">Resend code</button>
                </form>
            </div>
        </section>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=11"></script>
</body>
</html>
