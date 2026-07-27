package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.InventoryDao;
import models.*;
import util.DatabaseConnection;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private final InventoryDao dao=new InventoryDao();
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{
        HttpSession session=r.getSession();
        String message=(String)session.getAttribute("productMessage");
        session.removeAttribute("productMessage");
        show(r,s,message);
    }
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException{
        Employee user=(Employee)r.getSession().getAttribute("currentUser");
        String action=r.getParameter("action"),message;
        try(Connection c=DatabaseConnection.getConnection()){
            if("delete".equals(action)){
                if(!user.admin&&!user.deleteProducts){show(r,s,"You do not have permission to delete products.");return;}
                dao.deleteProduct(c,number(r.getParameter("id")));message="Product deleted successfully.";
            }else{
                int id=number(r.getParameter("id"));
                if(id>0&&!user.admin&&!user.updateProducts){show(r,s,"You do not have permission to update products.");return;}
                Product p=new Product();p.id=id;p.name=text(r.getParameter("name"));p.categoryId=number(r.getParameter("categoryId"));if(p.categoryId<1)p.categoryId=categoryId(c,text(r.getParameter("categoryName")));p.price=decimal(r.getParameter("price"));p.quantity=number(r.getParameter("quantity"));p.description=text(r.getParameter("description"));p.issueDate=text(r.getParameter("issueDate"));p.expiryDate=text(r.getParameter("expiryDate"));
                if(p.name.isEmpty()){show(r,s,"Enter the product name.");return;}
                if(p.categoryId<1){show(r,s,"Choose a category from the list.");return;}
                if(p.price<0){show(r,s,"Enter a valid product price.");return;}
                if(p.quantity<0){show(r,s,"Enter a valid product quantity.");return;}
                if(!p.issueDate.isEmpty()&&!p.expiryDate.isEmpty()&&p.expiryDate.compareTo(p.issueDate)<0){show(r,s,"Expiry date cannot be before issue date.");return;}
                dao.saveProduct(c,p);message=id==0?"Product added successfully.":"Product updated successfully.";
            }
            r.getSession().setAttribute("productMessage",message);
            s.sendRedirect(r.getContextPath()+"/products");
        }catch(Exception exception){show(r,s,"Operation failed: "+friendly(exception));}
    }
    private void show(HttpServletRequest r,HttpServletResponse s,String message)throws ServletException,IOException{try(Connection c=DatabaseConnection.getConnection()){int category=number(r.getParameter("category"));r.setAttribute("categories",dao.categories(c));r.setAttribute("products",dao.products(c,category));r.setAttribute("selectedCategory",category);r.setAttribute("message",message);r.getRequestDispatcher("/views/products.jsp").forward(r,s);}catch(Exception e){throw new ServletException(e);}}
    private int number(String x){try{return Integer.parseInt(x);}catch(Exception e){return 0;}}
    private double decimal(String x){try{return Double.parseDouble(x);}catch(Exception e){return -1;}}
    private int categoryId(Connection c,String name)throws java.sql.SQLException{for(Category category:dao.categories(c))if(category.name.equalsIgnoreCase(name))return category.id;return 0;}
    private String text(String x){return x==null?"":x.trim();}
    private String friendly(Exception e){String m=e.getMessage();return m!=null&&m.contains("unique constraint")?"This value already exists.":"Check the entered data.";}
}
