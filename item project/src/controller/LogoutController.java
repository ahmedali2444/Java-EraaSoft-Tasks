package controller;
import java.io.IOException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.*;
@WebServlet("/logout") public class LogoutController extends HttpServlet{protected void doPost(HttpServletRequest r,HttpServletResponse s)throws IOException{r.getSession().invalidate();s.sendRedirect("login");}}
