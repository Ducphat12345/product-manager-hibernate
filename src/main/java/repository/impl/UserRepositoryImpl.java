package repository.impl;

import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.UserRepository;
import utils.HibernateUtils;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAllUser() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "from User";
            Query<User> query = session.createQuery(sql);
            List<User> users = query.getResultList();
            session.getTransaction().commit();
            return users;
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
    public User findUserByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "select u from User u where u.username =:u_username";
            Query<User> query = session.createQuery(sql);
            query.setParameter("u_username", username);
            User user = query.getSingleResult();
            session.getTransaction().commit();
            return user;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }
}
