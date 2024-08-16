package com.mycompany.ecommerce.dao;

import com.mycompany.ecommerce.entities.Category;
import com.mycompany.ecommerce.entities.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author rishan
 */
public class ProductDao {

    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    public void saveProduct(Product product) throws HibernateException, Exception {

        Transaction tx = null;

        try (Session session = this.factory.openSession()) {

            tx = session.beginTransaction();

            session.persist(product);

            tx.commit();

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }

            throw e; // Re-throwing to handle at a higher level

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            throw e; // Re-throwing to handle at a higher level

        }
    }
}
