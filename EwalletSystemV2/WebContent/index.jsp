<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=5">
</head>
<body>
    <header class="site-header">
        <a class="brand" href="${pageContext.request.contextPath}/home">
            <svg class="brand-logo" viewBox="0 0 36 36" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#logo"></use></svg>
            <span>E-Wallet</span>
        </a>
        <a class="header-link" href="${pageContext.request.contextPath}/login">Sign in</a>
    </header>

    <main class="landing">
        <section>
            <h1 class="hero-title">Move money with confidence.</h1>
            <p class="hero-copy">Send, manage, and track every payment in one place.</p>
            <div class="button-row">
                <a class="button button-primary" href="${pageContext.request.contextPath}/register">Create account</a>
                <a class="button button-secondary" href="${pageContext.request.contextPath}/login">Sign in</a>
            </div>
        </section>

        <aside class="hero-card feature-card">
            <div class="feature-card-header">
                <div>
                    <p class="card-label">Wallet essentials</p>
                    <h2>Everything in one place</h2>
                </div>
                <span class="feature-indicator">●</span>
            </div>
            <div class="feature-grid">
                <article class="feature-item">
                    <span class="feature-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#send"></use></svg></span>
                    <div>
                        <strong>Transfers</strong>
                        <p>Send in seconds</p>
                    </div>
                </article>
                <article class="feature-item">
                    <span class="feature-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#activity"></use></svg></span>
                    <div>
                        <strong>Activity</strong>
                        <p>Track every move</p>
                    </div>
                </article>
                <article class="feature-item">
                    <span class="feature-icon"><svg class="ui-icon" viewBox="0 0 24 24" aria-hidden="true"><use href="${pageContext.request.contextPath}/assets/icons.svg#check"></use></svg></span>
                    <div>
                        <strong>Control</strong>
                        <p>Manage with ease</p>
                    </div>
                </article>
            </div>
        </aside>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>
