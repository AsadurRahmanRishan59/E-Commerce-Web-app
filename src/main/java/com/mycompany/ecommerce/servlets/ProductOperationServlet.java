package com.mycompany.ecommerce.servlets;

import com.mycompany.ecommerce.dao.CategoryDao;
import com.mycompany.ecommerce.entities.Category;
import com.mycompany.ecommerce.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author rishan
 */
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
                String saveCategory = "";
                try {

                    categoryDao.saveCategory(category);
                    httpSession.setAttribute("message", "New Category Added");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                } catch (Exception e) {

                    httpSession.setAttribute("message", "Category Title Must be Unique");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");
                    System.out.println("from exception " + saveCategory + " hi");

//                    e.printStackTrace();
                }
                /*
                if (saveCategory == "success") {

                    httpSession.setAttribute("message", "New Category Added");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                } else if (saveCategory == "not unique") {

                    httpSession.setAttribute("message", "Category Title Must be Unique");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                } else {

                    httpSession.setAttribute("message", "Could not be added");
                    httpSession.setAttribute("catTitle", catTitle);
                    response.sendRedirect("admin.jsp");

                }*/

            } else if (operation.trim().equals("addProduct")) {

                //fetching product data
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
