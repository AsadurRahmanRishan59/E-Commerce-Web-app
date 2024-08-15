package com.mycompany.ecommerce.dao;

import com.mycompany.ecommerce.entities.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rishan
 */
public class CategoryDao {

    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }

    //save category to database
    public void saveCategory(Category category) throws HibernateException, Exception {

        Transaction tx = null;

        try (Session session = this.factory.openSession()) {

            tx = session.beginTransaction();

            session.persist(category);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            
            e.printStackTrace();
            throw new HibernateException(e);

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
