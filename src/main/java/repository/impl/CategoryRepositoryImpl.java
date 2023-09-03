package repository.impl;

import model.Category;
import model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.CategoryRepository;
import utils.HibernateUtils;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> getAllCategory() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            List<Category> categories = session.createQuery("from Category").list();
            session.getTransaction().commit();
            return categories;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Category getCategoryByID(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query<Category> query = session.createQuery("select c from Category c where c.id =:c_id");
            query.setParameter("c_id", id);
            Category category = query.getSingleResult();
            session.getTransaction().commit();
            return category;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Category getCategoryByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query<Category> query = session.createQuery("select c from Category c where c.name =:c_name");
            query.setParameter("c_name", name);
            Category category = query.getSingleResult();
            session.getTransaction().commit();
            return category;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean save(Category category) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
