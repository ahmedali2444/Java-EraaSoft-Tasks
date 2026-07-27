package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.InventoryDao;
import models.Employee;
import util.DatabaseConnection;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {
    private final InventoryDao dao=new InventoryDao();
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{if(!admin(r,s))return;show(r,s,null);}
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{
        if(!admin(r,s))return;String action=r.getParameter("action");
        try(Connection c=DatabaseConnection.getConnection()){
            if("delete".equals(action)){dao.deleteEmployee(c,id(r.getParameter("id")));show(r,s,"Employee deleted successfully.");return;}
            Employee e=new Employee();e.id=id(r.getParameter("id"));e.name=value(r,"name");e.phone=value(r,"phone");e.email=value(r,"email");e.password=value(r,"password");e.admin=checked(r,"admin");e.updateProducts=checked(r,"updateProducts");e.deleteProducts=checked(r,"deleteProducts");e.updateCategories=checked(r,"updateCategories");e.deleteCategories=checked(r,"deleteCategories");e.active=checked(r,"active");
            if(e.name.isEmpty()||e.phone.isEmpty()||e.email.isEmpty()||(e.id==0&&e.password.length()<8)){show(r,s,"Complete the employee data and use a password of at least 8 characters.");return;}
            if(e.id==0)dao.addEmployee(c,e);else dao.updateEmployee(c,e);show(r,s,"Employee saved successfully.");
        }catch(Exception e){show(r,s,"Email or phone number already exists.");}
    }
    private boolean admin(HttpServletRequest r,HttpServletResponse s)throws IOException{Employee e=(Employee)r.getSession().getAttribute("currentUser");if(e==null||!e.admin){s.sendRedirect("products");return false;}return true;}
    private void show(HttpServletRequest r,HttpServletResponse s,String m)throws ServletException,IOException{try(Connection c=DatabaseConnection.getConnection()){r.setAttribute("employees",dao.employees(c));r.setAttribute("message",m);r.getRequestDispatcher("/views/employees.jsp").forward(r,s);}catch(Exception e){throw new ServletException(e);}}
    private String value(HttpServletRequest r,String n){return r.getParameter(n)==null?"":r.getParameter(n).trim();}
    private boolean checked(HttpServletRequest r,String n){return r.getParameter(n)!=null;}
    private int id(String x){try{return Integer.parseInt(x);}catch(Exception e){return 0;}}
}
