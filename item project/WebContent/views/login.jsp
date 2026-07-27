<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Login - Item Project</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css"><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login-layout.css"></head>
<body class="auth-body">
<main class="auth-layout">
    <section class="auth-visual" style="align-items:flex-start">
        <div class="visual-grid"></div>
        <div class="visual-copy">
            <span>INVENTORY WORKSPACE</span>
            <h1>Clear stock.<br>Better decisions.</h1>
            <p>A focused dashboard for products, categories, and your team.</p>
        </div>
        <div class="orb orb-one"></div>
        <div class="orb orb-two"></div>
    </section>
    <section class="auth-panel">
        <div class="auth-card">
            <div class="auth-brand"><span>IP</span><strong>Item Project</strong></div>
            <h2>Welcome back</h2>
            <p>Enter your credentials to continue.</p>
            <% if(request.getAttribute("message")!=null){ %>
                <div class="alert"><%=request.getAttribute("message")%></div>
            <% } %>
            <form method="post" action="<%=request.getContextPath()%>/login">
                <label>Email<input type="email" name="email" placeholder="name@company.com" required autofocus></label>
                <label>
                    Password
                    <div class="password-field">
                        <input id="loginPassword" type="password" name="password" placeholder="Enter your password" required>
                        <button type="button" data-password-toggle="loginPassword" aria-label="Show password">
                            <svg viewBox="0 0 24 24"><path d="M2.5 12s3.5-6 9.5-6 9.5 6 9.5 6-3.5 6-9.5 6-9.5-6-9.5-6Z"/><circle cx="12" cy="12" r="2.5"/></svg>
                        </button>
                    </div>
                </label>
                <button class="primary-button" type="submit">Login</button>
            </form>
        </div>
    </section>
</main>
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</body>
</html>
