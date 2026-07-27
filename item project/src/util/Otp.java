package util;

import java.security.SecureRandom;
import javax.servlet.http.HttpSession;

public final class Otp {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final long LIFE_TIME = 10 * 60 * 1000;
    private Otp() {}

    public static String issue(HttpSession session) {
        String code = String.format("%06d", RANDOM.nextInt(1_000_000));
        session.setAttribute("otpCode", code);
        session.setAttribute("otpIssuedAt", System.currentTimeMillis());
        session.setAttribute("otpAttempts", 0);
        return code;
    }

    public static boolean verify(HttpSession session, String enteredCode) {
        Object code = session.getAttribute("otpCode");
        Object issuedAt = session.getAttribute("otpIssuedAt");
        Object attempts = session.getAttribute("otpAttempts");
        if (!(code instanceof String) || !(issuedAt instanceof Long) || !(attempts instanceof Integer)) return false;
        if ((Integer) attempts >= 5 || System.currentTimeMillis() - (Long) issuedAt > LIFE_TIME) return false;
        if (code.equals(enteredCode)) {
            clear(session);
            return true;
        }
        session.setAttribute("otpAttempts", (Integer) attempts + 1);
        return false;
    }

    public static void clear(HttpSession session) {
        session.removeAttribute("otpCode");
        session.removeAttribute("otpIssuedAt");
        session.removeAttribute("otpAttempts");
    }
}
