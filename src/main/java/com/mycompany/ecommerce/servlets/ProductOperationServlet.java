package com.mycompany.ecommerce.servlets;

import com.mycompany.ecommerce.dao.CategoryDao;
import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.entities.Category;
import com.mycompany.ecommerce.entities.Product;
import com.mycompany.ecommerce.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author rishan
 */
@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operation = request.getParameter("operation");

            if (operation.trim().equals("addCategory")) {

                //fetching category data
                String catTitle = request.getParameter("catTitle").toUpperCase();
                String catDescription = request.getParameter("catDescription");

                Category category = new Category();
                category.setCategoryTitle(catTitle);
                category.setCategoryDecription(catDescription);

                //save on database
                CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());

                HttpSession httpSession = request.getSession();

                try {

                    categoryDao.saveCategory(category);
                    httpSession.setAttribute("message", "New Category Added");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                } catch (ConstraintViolationException e) {

                    httpSession.setAttribute("message", "Category Title Must be Unique");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                    e.printStackTrace();
                } catch (HibernateException e) {

                    httpSession.setAttribute("message", catTitle + " Could not be added-category");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                    e.printStackTrace();
                } catch (Exception e) {

                    httpSession.setAttribute("message", "Could not be added-category");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                    e.printStackTrace();
                    System.out.println("from exception-------------------------------------------------===============");
                }

            } else if (operation.trim().equals("addProduct")) {


                //fetching product data
                String productName = request.getParameter("productName");
                String productDescription = request.getParameter("ProductDescription");

                Part part = request.getPart("productPic");
                String productPic = part.getSubmittedFileName();

                int productPrice = Integer.parseInt(request.getParameter("productPrice"));
                int productDiscount = Integer.parseInt(request.getParameter("productDiscount"));
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                int catId = Integer.parseInt(request.getParameter("catId"));

                Category category = new CategoryDao(FactoryProvider.getFactory()).getCategoryByCatId(catId);
                Product product = new Product(productName, productDescription, productPic, productPrice, productDiscount, productQuantity, category);

                ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
                HttpSession httpSession = request.getSession();

                try {

                    productDao.saveProduct(product);
                    httpSession.setAttribute("message", "New Product Added");
                    httpSession.setAttribute("productName", productName);
                    response.sendRedirect("admin.jsp");

                } catch (HibernateException e) {

                    httpSession.setAttribute("message", productName + " Could not be added");
                    httpSession.setAttribute("productName", productName);
                    response.sendRedirect("admin.jsp");

                    e.printStackTrace();
                } catch (Exception e) {

                    httpSession.setAttribute("message", "Could not be added");
                    httpSession.setAttribute("productName", productName);
                    response.sendRedirect("admin.jsp");

                    e.printStackTrace();
                }
                
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
