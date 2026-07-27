package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.InventoryDao;
import models.Employee;
import services.EmailService;
import services.implementation.SmtpEmailService;
import util.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final InventoryDao dao = new InventoryDao();
    private final EmailService emailService = new SmtpEmailService();
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
        if(request.getSession(false)!=null&&request.getSession(false).getAttribute("currentUser")!=null){response.sendRedirect("products");return;}
        request.getRequestDispatcher("/views/login.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
        try(Connection connection=DatabaseConnection.getConnection()){
            Employee employee=dao.findUser(connection,request.getParameter("email"),request.getParameter("password"));
            if(employee==null){show(request,response,"Incorrect email or password.");return;}
            HttpSession session=request.getSession();
            session.setAttribute("pendingUser",employee);
            String code=Otp.issue(session);
            if(!emailService.sendOtp(employee.email,code)){show(request,response,"OTP email could not be sent. Check SMTP settings.");return;}
            response.sendRedirect("verify-otp");
        }catch(Exception exception){show(request,response,"Cannot connect to the database.");}
    }
    private void show(HttpServletRequest request,HttpServletResponse response,String message)throws ServletException,IOException{request.setAttribute("message",message);request.getRequestDispatcher("/views/login.jsp").forward(request,response);}
}
