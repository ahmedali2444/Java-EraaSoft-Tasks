package controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import models.Employee;
import services.EmailService;
import services.implementation.SmtpEmailService;
import util.Otp;

@WebServlet("/verify-otp")
public class OtpController extends HttpServlet {
    private final EmailService emailService=new SmtpEmailService();
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{if(r.getSession().getAttribute("pendingUser")==null){s.sendRedirect("login");return;}r.getRequestDispatcher("/views/otp.jsp").forward(r,s);}
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{
        HttpSession session=r.getSession();
        if("resend".equals(r.getParameter("action"))){Employee e=(Employee)session.getAttribute("pendingUser");String code=Otp.issue(session);if(e!=null&&emailService.sendOtp(e.email,code)){r.setAttribute("message","A new code was sent.");}else{r.setAttribute("message","The code could not be sent.");}r.getRequestDispatcher("/views/otp.jsp").forward(r,s);return;}
        if(Otp.verify(session,r.getParameter("otp"))){session.setAttribute("currentUser",session.getAttribute("pendingUser"));session.removeAttribute("pendingUser");s.sendRedirect("products");return;}
        r.setAttribute("message","Invalid, expired, or locked OTP.");r.getRequestDispatcher("/views/otp.jsp").forward(r,s);
    }
}
