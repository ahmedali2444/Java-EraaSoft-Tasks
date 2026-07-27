document.addEventListener("DOMContentLoaded", function () {
    var alerts = document.querySelectorAll(".alert");
    var forms = document.querySelectorAll("form");
    var password = document.getElementById("password");
    var registerForm = document.querySelector(".register-form");
    var passwordToggles = document.querySelectorAll("[data-password-toggle]");
    var dialogTriggers = document.querySelectorAll("[data-dialog-trigger]");
    var dialogCloseButtons = document.querySelectorAll("[data-dialog-close]");
    var cardNumberInput = document.querySelector("input[name='cardNumber']");
    var expiryDateInput = document.querySelector("input[name='expiryDate']");
    var historyFilterButtons = document.querySelectorAll("[data-history-filter]");
    var historyItems = document.querySelectorAll("[data-history-category]");
    var historyFilterEmpty = document.querySelector(".history-filter-empty");
    var adminActionTriggers = document.querySelectorAll("[data-admin-action-trigger]");
    var adminActionDialog = document.getElementById("adminActionDialog");
    var adminActionInput = document.querySelector("[data-admin-action-input]");
    var adminEmailInput = document.querySelector("[data-admin-email-input]");
    var adminActionTitle = document.querySelector("[data-admin-action-title]");
    var adminActionCopy = document.querySelector("[data-admin-action-copy]");
    var adminActionSubmit = document.querySelector("[data-admin-action-submit]");
    var adminEditTriggers = document.querySelectorAll("[data-admin-edit-trigger]");
    var adminEditDialog = document.getElementById("adminEditDialog");
    var adminEditCurrentEmail = document.querySelector("[data-admin-edit-current-email]");
    var adminEditEmail = document.querySelector("[data-admin-edit-email]");
    var adminEditPhone = document.querySelector("[data-admin-edit-phone]");
    var adminEditAge = document.querySelector("[data-admin-edit-age]");
    var adminEditBalance = document.querySelector("[data-admin-edit-balance]");
    var adminEditStatus = document.querySelector("[data-admin-edit-status]");
    var adminUserSearch = document.querySelector("[data-admin-user-search]");
    var adminUserStatus = document.querySelector("[data-admin-user-status]");
    var adminUserRole = document.querySelector("[data-admin-user-role]");
    var adminUserRows = document.querySelectorAll("[data-admin-user-row]");
    var adminUsersEmpty = document.querySelector("[data-admin-users-empty]");
    var adminUserCount = document.querySelector("[data-admin-user-count]");
    var adminTransactionSearch = document.querySelector("[data-admin-transaction-search]");
    var adminTransactionCategory = document.querySelector("[data-admin-transaction-category]");
    var adminTransactionType = document.querySelector("[data-admin-transaction-type]");
    var adminTransactionRows = document.querySelectorAll("[data-admin-transaction-row]");
    var adminTransactionsEmpty = document.querySelector("[data-admin-transactions-empty]");
    var adminTransactionCount = document.querySelector("[data-admin-transaction-count]");
    var otpForm = document.querySelector("[data-otp-form]");
    var otpInputs = document.querySelectorAll("[data-otp-input]");
    var otpValueInput = document.querySelector("[data-otp-value]");
    var otpError = document.querySelector("[data-otp-error]");
    var operationConfirmForm = document.querySelector("[data-operation-confirm-form]");
    var operationConfirmDialog = document.getElementById("operationConfirmDialog");
    var operationConfirmTitle = document.querySelector("[data-operation-confirm-title]");
    var operationConfirmAction = document.querySelector("[data-operation-confirm-action]");
    var operationConfirmAmount = document.querySelector("[data-operation-confirm-amount]");
    var operationConfirmCard = document.querySelector("[data-operation-confirm-card]");
    var operationConfirmCardRow = document.querySelector("[data-operation-confirm-card-row]");
    var operationConfirmRecipient = document.querySelector("[data-operation-confirm-recipient]");
    var operationConfirmRecipientRow = document.querySelector("[data-operation-confirm-recipient-row]");
    var operationConfirmSubmit = document.querySelector("[data-operation-confirm-submit]");
    var adminEditForm = document.querySelector("[data-admin-edit-form]");
    var adminEditConfirmDialog = document.getElementById("adminEditConfirmDialog");
    var adminEditConfirmSubmit = document.querySelector("[data-admin-edit-confirm-submit]");
    var adminConfirmEmail = document.querySelector("[data-admin-confirm-email]");
    var adminConfirmPhone = document.querySelector("[data-admin-confirm-phone]");
    var adminConfirmAge = document.querySelector("[data-admin-confirm-age]");
    var adminConfirmBalance = document.querySelector("[data-admin-confirm-balance]");
    var adminConfirmStatus = document.querySelector("[data-admin-confirm-status]");

    alerts.forEach(function (alert) {
        if (alert.textContent.trim() === "") {
            alert.remove();
            return;
        }

        window.setTimeout(function () {
            alert.classList.add("is-hiding");
            window.setTimeout(function () { alert.remove(); }, 220);
        }, 4000);
    });

    if (cardNumberInput) {
        cardNumberInput.addEventListener("input", function () {
            var digits = cardNumberInput.value.replace(/\D/g, "").slice(0, 16);
            cardNumberInput.value = digits.replace(/(\d{4})(?=\d)/g, "$1 ");
            cardNumberInput.setCustomValidity(digits.length > 0 && digits.length !== 16
                ? "Card number must contain exactly 16 digits."
                : "");
        });
    }

    if (expiryDateInput) {
        expiryDateInput.addEventListener("input", function () {
            var digits = expiryDateInput.value.replace(/\D/g, "").slice(0, 4);
            var month = Number(digits.slice(0, 2));
            expiryDateInput.value = digits.length > 2
                ? digits.slice(0, 2) + "/" + digits.slice(2)
                : digits;
            expiryDateInput.setCustomValidity(digits.length > 0 &&
                (digits.length !== 4 || month < 1 || month > 12)
                ? "Use a valid expiry date in MM/YY format."
                : "");
        });
    }

    historyFilterButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            var selectedCategory = button.getAttribute("data-history-filter");
            var visibleCount = 0;

            historyFilterButtons.forEach(function (filterButton) {
                filterButton.classList.toggle("active", filterButton === button);
            });

            historyItems.forEach(function (item) {
                var shouldShow = selectedCategory === "all" ||
                    item.getAttribute("data-history-category") === selectedCategory;
                item.hidden = !shouldShow;
                if (shouldShow) { visibleCount++; }
            });

            if (historyFilterEmpty) {
                historyFilterEmpty.hidden = visibleCount !== 0;
            }
        });
    });

    adminActionTriggers.forEach(function (trigger) {
        trigger.addEventListener("click", function () {
            if (!adminActionDialog) { return; }

            var action = trigger.getAttribute("data-admin-action-trigger");
            var email = trigger.getAttribute("data-admin-email");
            var isDelete = action === "delete";

            if (adminActionInput) { adminActionInput.value = action; }
            if (adminEmailInput) { adminEmailInput.value = email; }
            if (adminActionTitle) { adminActionTitle.textContent = isDelete ? "Delete this account?" : "Deactivate this account?"; }
            if (adminActionCopy) {
                adminActionCopy.textContent = isDelete
                    ? "This permanently deletes " + email + " and its associated wallet data."
                    : email + " will no longer be able to sign in or use the wallet.";
            }
            if (adminActionSubmit) { adminActionSubmit.textContent = isDelete ? "Delete account" : "Deactivate account"; }
            adminActionDialog.showModal();
        });
    });

    adminEditTriggers.forEach(function (trigger) {
        trigger.addEventListener("click", function () {
            if (!adminEditDialog) { return; }

            if (adminEditCurrentEmail) { adminEditCurrentEmail.value = trigger.getAttribute("data-admin-current-email"); }
            if (adminEditEmail) { adminEditEmail.value = trigger.getAttribute("data-admin-email"); }
            if (adminEditPhone) { adminEditPhone.value = trigger.getAttribute("data-admin-phone"); }
            if (adminEditAge) { adminEditAge.value = trigger.getAttribute("data-admin-age"); }
            if (adminEditBalance) { adminEditBalance.value = trigger.getAttribute("data-admin-balance"); }
            if (adminEditStatus) { adminEditStatus.value = trigger.getAttribute("data-admin-active"); }
            adminEditDialog.showModal();
        });
    });

    function filterAdminUsers() {
        if (!adminUserSearch || !adminUserStatus || !adminUserRole) { return; }
        var search = adminUserSearch.value.trim().toLowerCase();
        var status = adminUserStatus.value;
        var role = adminUserRole.value;
        var visibleCount = 0;

        adminUserRows.forEach(function (row) {
            var shouldShow = (!search || row.getAttribute("data-email").indexOf(search) !== -1)
                && (status === "all" || row.getAttribute("data-status") === status)
                && (role === "all" || row.getAttribute("data-role") === role);
            row.hidden = !shouldShow;
            if (shouldShow) { visibleCount++; }
        });

        if (adminUsersEmpty) { adminUsersEmpty.hidden = visibleCount !== 0; }
        if (adminUserCount) { adminUserCount.textContent = visibleCount; }
    }

    [adminUserSearch, adminUserStatus, adminUserRole].forEach(function (control) {
        if (control) {
            control.addEventListener(control === adminUserSearch ? "input" : "change", filterAdminUsers);
        }
    });

    function filterAdminTransactions() {
        if (!adminTransactionSearch || !adminTransactionCategory || !adminTransactionType) { return; }
        var search = adminTransactionSearch.value.trim().toLowerCase();
        var category = adminTransactionCategory.value;
        var type = adminTransactionType.value;
        var visibleCount = 0;

        adminTransactionRows.forEach(function (row) {
            var shouldShow = (!search || row.getAttribute("data-search").indexOf(search) !== -1)
                && (category === "all" || row.getAttribute("data-category") === category)
                && (type === "all" || row.getAttribute("data-type") === type);
            row.hidden = !shouldShow;
            if (shouldShow) { visibleCount++; }
        });

        if (adminTransactionsEmpty) { adminTransactionsEmpty.hidden = visibleCount !== 0; }
        if (adminTransactionCount) { adminTransactionCount.textContent = visibleCount; }
    }

    [adminTransactionSearch, adminTransactionCategory, adminTransactionType].forEach(function (control) {
        if (control) {
            control.addEventListener(control === adminTransactionSearch ? "input" : "change", filterAdminTransactions);
        }
    });

    if (adminTransactionCategory && adminTransactionCategory.value === "financial") {
        filterAdminTransactions();
    }

    function formatConfirmationAmount(value) {
        var amount = Number(value);
        if (!Number.isFinite(amount)) { return "$ 0.00"; }
        return "$ " + amount.toLocaleString("en-US", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
    }

    function setConfirmationText(element, value) {
        if (element) { element.textContent = value || "—"; }
    }

    if (operationConfirmForm && operationConfirmDialog && operationConfirmSubmit) {
        var operationConfirmed = false;

        operationConfirmForm.addEventListener("submit", function (event) {
            if (operationConfirmed) { return; }
            if (!operationConfirmForm.checkValidity()) { return; }

            event.preventDefault();
            var actionInput = operationConfirmForm.querySelector("input[name='action']");
            var action = actionInput ? actionInput.value : "";
            var actionLabels = {
                deposit: "Deposit",
                withdraw: "Withdrawal",
                transfer: "Transfer"
            };
            var amountInput = operationConfirmForm.querySelector("input[name='amount']");
            var cardOption = operationConfirmForm.querySelector("select[name='cardId'] option:checked");
            var recipientInput = operationConfirmForm.querySelector("input[name='recipient']");
            var actionLabel = actionLabels[action] || "Operation";

            setConfirmationText(operationConfirmTitle, "Confirm " + actionLabel.toLowerCase() + "?");
            setConfirmationText(operationConfirmAction, actionLabel);
            setConfirmationText(operationConfirmAmount, formatConfirmationAmount(amountInput ? amountInput.value : 0));
            if (operationConfirmCardRow) { operationConfirmCardRow.hidden = action === "transfer"; }
            if (operationConfirmRecipientRow) { operationConfirmRecipientRow.hidden = action !== "transfer"; }
            setConfirmationText(operationConfirmCard, cardOption ? cardOption.textContent.trim() : "");
            setConfirmationText(operationConfirmRecipient, recipientInput ? recipientInput.value.trim() : "");
            operationConfirmSubmit.textContent = "Confirm " + actionLabel;
            operationConfirmDialog.showModal();
        });

        operationConfirmSubmit.addEventListener("click", function () {
            operationConfirmed = true;
            operationConfirmSubmit.disabled = true;
            operationConfirmDialog.close();
            operationConfirmForm.requestSubmit();
        });
    }

    if (adminEditForm && adminEditConfirmDialog && adminEditConfirmSubmit) {
        var adminEditConfirmed = false;

        adminEditForm.addEventListener("submit", function (event) {
            if (adminEditConfirmed) { return; }
            if (!adminEditForm.checkValidity()) { return; }

            event.preventDefault();
            var emailInput = adminEditForm.querySelector("[data-admin-edit-email]");
            var phoneInput = adminEditForm.querySelector("[data-admin-edit-phone]");
            var ageInput = adminEditForm.querySelector("[data-admin-edit-age]");
            var balanceInput = adminEditForm.querySelector("[data-admin-edit-balance]");
            var statusInput = adminEditForm.querySelector("[data-admin-edit-status]");

            setConfirmationText(adminConfirmEmail, emailInput ? emailInput.value.trim() : "");
            setConfirmationText(adminConfirmPhone, phoneInput ? phoneInput.value.trim() : "");
            setConfirmationText(adminConfirmAge, ageInput ? ageInput.value : "");
            setConfirmationText(adminConfirmBalance, formatConfirmationAmount(balanceInput ? balanceInput.value : 0));
            setConfirmationText(adminConfirmStatus, statusInput && statusInput.value === "true" ? "Active" : "Inactive");
            adminEditConfirmDialog.showModal();
        });

        adminEditConfirmSubmit.addEventListener("click", function () {
            adminEditConfirmed = true;
            adminEditConfirmSubmit.disabled = true;
            adminEditConfirmDialog.close();
            adminEditForm.requestSubmit();
        });
    }

    if (otpForm && otpInputs.length === 6 && otpValueInput) {
        function syncOtpValue() {
            var value = Array.prototype.map.call(otpInputs, function (input) {
                return input.value.replace(/\D/g, "").slice(-1);
            }).join("");
            otpValueInput.value = value;
            return value;
        }

        function setOtpError(message) {
            if (otpError) { otpError.textContent = message || ""; }
        }

        function fillOtpInputs(value) {
            var digits = (value || "").replace(/\D/g, "").slice(0, 6);
            Array.prototype.forEach.call(otpInputs, function (input, index) {
                input.value = digits.charAt(index) || "";
            });
            syncOtpValue();
            setOtpError("");

            var focusIndex = Math.min(digits.length, otpInputs.length - 1);
            otpInputs[focusIndex].focus();
            otpInputs[focusIndex].select();
        }

        Array.prototype.forEach.call(otpInputs, function (input, index) {
            input.addEventListener("input", function () {
                var digits = input.value.replace(/\D/g, "");
                if (digits.length > 1) {
                    fillOtpInputs(digits);
                    return;
                }

                input.value = digits;
                syncOtpValue();
                setOtpError("");
                if (digits && index < otpInputs.length - 1) {
                    otpInputs[index + 1].focus();
                    otpInputs[index + 1].select();
                }
            });

            input.addEventListener("paste", function (event) {
                event.preventDefault();
                var clipboard = event.clipboardData || window.clipboardData;
                fillOtpInputs(clipboard ? clipboard.getData("text") : "");
            });

            input.addEventListener("keydown", function (event) {
                if (event.key === "Backspace" && !input.value && index > 0) {
                    otpInputs[index - 1].focus();
                    otpInputs[index - 1].select();
                } else if (event.key === "ArrowLeft" && index > 0) {
                    event.preventDefault();
                    otpInputs[index - 1].focus();
                    otpInputs[index - 1].select();
                } else if (event.key === "ArrowRight" && index < otpInputs.length - 1) {
                    event.preventDefault();
                    otpInputs[index + 1].focus();
                    otpInputs[index + 1].select();
                }
            });
        });

        otpForm.addEventListener("submit", function (event) {
            if (syncOtpValue().length !== 6) {
                event.preventDefault();
                setOtpError("Enter all 6 digits to continue.");
                for (var index = 0; index < otpInputs.length; index++) {
                    if (!otpInputs[index].value) {
                        otpInputs[index].focus();
                        break;
                    }
                }
            }
        });
    }

    forms.forEach(function (form) {
        if (form.hasAttribute("data-skip-loading-state")) {
            return;
        }
        form.addEventListener("submit", function () {
            var button = form.querySelector("button[type='submit']");

            if (button) {
                button.disabled = true;
                button.textContent = "Processing...";
            }
        });
    });

    passwordToggles.forEach(function (toggle) {
        toggle.addEventListener("click", function () {
            var target = document.getElementById(toggle.getAttribute("data-target"));
            var isHidden = target.type === "password";

            target.type = isHidden ? "text" : "password";
            toggle.classList.toggle("is-visible", isHidden);
            toggle.setAttribute("aria-label", isHidden ? "Hide password" : "Show password");
        });
    });

    dialogTriggers.forEach(function (trigger) {
        trigger.addEventListener("click", function () {
            var dialog = document.getElementById(trigger.getAttribute("data-dialog-trigger"));
            if (dialog) { dialog.showModal(); }
        });
    });

    dialogCloseButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            var dialog = button.closest("dialog");
            if (dialog) { dialog.close(); }
        });
    });

    document.querySelectorAll("dialog").forEach(function (dialog) {
        dialog.addEventListener("click", function (event) {
            if (event.target === dialog) { dialog.close(); }
        });
    });

    var initialDialog = document.body.getAttribute("data-open-dialog");
    if (initialDialog) {
        var dialog = document.getElementById(initialDialog);
        if (dialog) { dialog.showModal(); }
    }

    if (password && registerForm) {
        var passwordStatus = document.getElementById("passwordStatus");
        var confirmPassword = document.getElementById("confirmPassword");
        var passwordMatchMessage = document.getElementById("passwordMatchMessage");
        var submitButton = registerForm.querySelector("button[type='submit']");
        var rules = {
            length: registerForm.querySelector("[data-rule='length']"),
            upper: registerForm.querySelector("[data-rule='upper']"),
            lower: registerForm.querySelector("[data-rule='lower']"),
            number: registerForm.querySelector("[data-rule='number']")
        };

        function updatePasswordStrength() {
            var value = password.value;
            var checks = {
                length: value.length >= 8,
                upper: /[A-Z]/.test(value),
                lower: /[a-z]/.test(value),
                number: /[0-9]/.test(value)
            };
            var score = 0;

            Object.keys(checks).forEach(function (key) {
                if (checks[key]) {
                    score++;
                }
                rules[key].classList.toggle("is-valid", checks[key]);
            });

            var isStrong = score === 4;
            var passwordsMatch = confirmPassword.value.length > 0 && password.value === confirmPassword.value;

            if (isStrong) {
                passwordStatus.textContent = "Strong password";
                passwordStatus.className = "password-status strong";
                password.setCustomValidity("");
            } else {
                passwordStatus.textContent = value.length === 0 ? "Required" : "Password is weak";
                passwordStatus.className = "password-status weak";
                password.setCustomValidity("Password does not meet all requirements.");
            }

            var hasConfirmation = confirmPassword.value.length > 0;
            passwordMatchMessage.hidden = !hasConfirmation;
            if (passwordsMatch) {
                passwordMatchMessage.textContent = "Passwords match.";
                passwordMatchMessage.className = "password-match-message is-valid";
                confirmPassword.setCustomValidity("");
            } else if (hasConfirmation) {
                passwordMatchMessage.textContent = "Passwords do not match.";
                passwordMatchMessage.className = "password-match-message";
                confirmPassword.setCustomValidity("Passwords do not match.");
            } else {
                passwordMatchMessage.textContent = "";
                passwordMatchMessage.className = "password-match-message";
                confirmPassword.setCustomValidity("");
            }

            submitButton.disabled = !isStrong || !passwordsMatch;
        }

        password.addEventListener("input", updatePasswordStrength);
        confirmPassword.addEventListener("input", updatePasswordStrength);
        updatePasswordStrength();
    }
});
