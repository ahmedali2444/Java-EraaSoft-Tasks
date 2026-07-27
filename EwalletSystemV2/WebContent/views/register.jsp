<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create account - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=6">
</head>
<body class="register-page">
    <header class="site-header">
        <a class="brand" href="${pageContext.request.contextPath}/home">
            <svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg>
            <span>E-Wallet</span>
        </a>
        <a class="header-link" href="${pageContext.request.contextPath}/login">Sign in</a>
    </header>

    <main class="auth-page">
        <section class="auth-card">
            <h1 class="page-title">Create your account</h1>
            <p class="alert">${message}</p>

            <form class="form-stack register-form" action="${pageContext.request.contextPath}/register" method="post">
                <div class="field register-email-field">
                    <label for="email">Email</label>
                    <input id="email" type="email" name="email" placeholder="name@example.com" required>
                </div>

                <div class="register-details">
                    <div class="field">
                        <label for="phoneNumber">Phone number</label>
                        <input id="phoneNumber" type="text" name="phoneNumber" placeholder="+201012345678" required>
                    </div>
                    <div class="field age-field">
                        <label for="age">Age</label>
                        <input id="age" type="number" name="age" min="18" placeholder="18" required>
                    </div>
                </div>

                <div class="field register-password-field">
                    <label for="password">Password <span class="password-status" id="passwordStatus">Required</span></label>
                    <div class="password-input">
                        <input id="password" type="password" name="password" placeholder="Enter a strong password" aria-describedby="passwordRules" required>
                        <button class="password-toggle" type="button" data-password-toggle data-target="password" aria-label="Show password">
                            <svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#eye"></use></svg>
                        </button>
                    </div>
                    <ul class="password-rules" id="passwordRules">
                        <li data-rule="length">8+ characters</li>
                        <li data-rule="upper">Uppercase letter</li>
                        <li data-rule="lower">Lowercase letter</li>
                        <li data-rule="number">Number</li>
                    </ul>
                </div>

                <div class="field confirm-password-field">
                    <label for="confirmPassword">Confirm password</label>
                    <div class="password-input">
                        <input id="confirmPassword" type="password" name="confirmPassword" placeholder="Repeat your password" required>
                        <button class="password-toggle" type="button" data-password-toggle data-target="confirmPassword" aria-label="Show password">
                            <svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#eye"></use></svg>
                        </button>
                    </div>
                    <p class="password-match-message" id="passwordMatchMessage" hidden></p>
                </div>

                <button class="form-submit" type="submit">Create account</button>
            </form>

            <p class="form-footer">Already have an account? <a class="text-link" href="${pageContext.request.contextPath}/login">Sign in</a></p>
        </section>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=15"></script>
</body>
</html>
