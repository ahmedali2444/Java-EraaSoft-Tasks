package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.InventoryDao;
import models.Employee;
import util.DatabaseConnection;

@WebServlet("/categories")
public class CategoryController extends HttpServlet {
    private final InventoryDao dao=new InventoryDao();
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{show(r,s,null);}
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{
        Employee u=(Employee)r.getSession().getAttribute("currentUser");String action=r.getParameter("action");
        if(!u.admin&&!u.updateCategories){show(r,s,"You do not have permission to manage categories.");return;}
        try(Connection c=DatabaseConnection.getConnection()){
            if("delete".equals(action)){if(!u.admin&&!u.deleteCategories){show(r,s,"You do not have permission to delete categories.");return;}dao.deleteCategory(c,id(r.getParameter("id")));show(r,s,"Category deleted successfully.");}
            else{String name=r.getParameter("name")==null?"":r.getParameter("name").trim();if(name.isEmpty()){show(r,s,"Category name is required.");return;}if("update".equals(action))dao.updateCategory(c,id(r.getParameter("id")),name);else dao.category(c,name);if("products".equals(r.getParameter("returnTo"))){s.sendRedirect("products");return;}show(r,s,"Category saved successfully.");}
        }catch(Exception e){show(r,s,e.getMessage()!=null&&e.getMessage().contains("child record")?"This category contains products and cannot be deleted.":"Category name already exists.");}
    }
    private void show(HttpServletRequest r,HttpServletResponse s,String m)throws ServletException,IOException{try(Connection c=DatabaseConnection.getConnection()){r.setAttribute("categories",dao.categories(c));r.setAttribute("message",m);r.getRequestDispatcher("/views/categories.jsp").forward(r,s);}catch(Exception e){throw new ServletException(e);}}
    private int id(String x){try{return Integer.parseInt(x);}catch(Exception e){return 0;}}
}
