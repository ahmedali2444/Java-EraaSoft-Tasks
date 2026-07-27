<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in - E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
</head>
<body>
    <header class="site-header">
        <a class="brand" href="${pageContext.request.contextPath}/home">
            <svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg>
            <span>E-Wallet</span>
        </a>
        <a class="header-link" href="${pageContext.request.contextPath}/register">Create account</a>
    </header>

    <main class="auth-page">
        <section class="auth-card">
            <h1 class="page-title">Login</h1>
            <p class="page-copy">Enter your details to access your wallet.</p>
            <p class="alert">${message}</p>

            <form class="form-stack" action="${pageContext.request.contextPath}/login" method="post">
                <div class="field">
                    <label for="email">Email</label>
                    <input id="email" type="email" name="email" placeholder="name@example.com" required>
                </div>
                <div class="field">
                    <label for="password">Password</label>
                    <div class="password-input">
                        <input id="password" type="password" name="password" placeholder="Enter your password" required>
                        <button class="password-toggle" type="button" data-password-toggle data-target="password" aria-label="Show password">
                            <svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#eye"></use></svg>
                        </button>
                    </div>
                </div>
                <button class="form-submit" type="submit">Sign in</button>
            </form>

            <p class="form-footer">New to E-Wallet? <a class="text-link" href="${pageContext.request.contextPath}/register">Create an account</a></p>
        </section>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main.js?v=10"></script>
</body>
</html>
