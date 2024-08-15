package com.mycompany.ecommerce.servlets;

import com.mycompany.ecommerce.entities.User;
import com.mycompany.ecommerce.helper.FactoryProvider;
import com.mycompany.ecommerce.helper.PasswordUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rishan
 */
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Session hibernateSession = null;
            Transaction tx = null;

            try {
                hibernateSession = FactoryProvider.getFactory().openSession();
                tx = hibernateSession.beginTransaction();

                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");

                // hashing the password before saving
                String hashPassword = PasswordUtil.hashPassword(password);


                // Creating user
                User user = new User(username, email, hashPassword, phone, "default.jpg", address, "normal");

                hibernateSession.persist(user);

                tx.commit();

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Registration Successful!!");
                response.sendRedirect("register.jsp");

            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();  // Rollback the transaction on exception
                }
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Email must be unique!!");
                response.sendRedirect("register.jsp");

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }

            } finally {
                if (hibernateSession != null && hibernateSession.isOpen()) {
                    hibernateSession.close();
                }
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for handling user registration";
    }

}
