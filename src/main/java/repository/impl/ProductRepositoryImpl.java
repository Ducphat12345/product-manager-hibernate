package repository.impl;

import model.Category;
import model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.ProductRepository;
import utils.HibernateUtils;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> getAllProduct() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query<Product> query = session.createQuery("from Product");
            List<Product> products = query.list();
            session.getTransaction().commit();
            return products;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }
    @Override
    public List<Product> getProductByCategory(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "select p from Product p inner join Category c on p.category=c.id where c.id =:c_id";
            Query<Product> query = session.createQuery(sql);
            query.setParameter("c_id", id);
            List<Product> products = query.getResultList();
            session.getTransaction().commit();
            return products;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }
    @Override
    public Product getProductByID(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "select p from Product p where p.id =:p_id";
            Query<Product> query = session.createQuery(sql);
            query.setParameter("p_id", id);
            Product product = query.getSingleResult();
            session.getTransaction().commit();
            return product;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "select p from Product p where p.name =:p_name";
            Query<Product> query = session.createQuery(sql);
            query.setParameter("p_name", name);
            Product product = query.getSingleResult();
            session.getTransaction().commit();
            return product;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean save(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(product);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(product);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(product);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return false;
    }
}
