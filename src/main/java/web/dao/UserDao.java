package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.config.HibernateConfig;
import web.model.User;

import javax.persistence.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityTransaction tx = null;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Transactional
    public void createUsersTable() {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.createNativeQuery("" +
                            "CREATE TABLE IF NOT EXISTS users ( " +
                            "id SERIAL PRIMARY KEY, " +
                            "name VARCHAR(255), " +
                            "lastname VARCHAR(255), " +
                            "age SMALLINT);" +
                            "")
                    .executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }
    }
    @Transactional
    public void saveUser(String name, String lastName, byte age) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.createNativeQuery("INSERT INTO users (name, lastName, age) VALUES (?,?,?);")
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }
    }

    @Transactional
    public void dropUsersTable() {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.createNativeQuery("DROP TABLE IF EXISTS users;")
                    .executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }
    }

    @Transactional
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            usersList = em.createQuery("SELECT u FROM User u").getResultList();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }

        return usersList;
    }
    @Transactional
    public User getUserById(long id) {
        List<User> usersList = new ArrayList<>();
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            usersList = em.createQuery("SELECT u FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }
        return usersList.get(0);
    }

    @Transactional
    public void updateUser(long id, String name, String lastname, byte age) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.createQuery("UPDATE User u " +
                            "SET u.name = :name, " +
                            "u.lastname = :lastname, " +
                            "u.age = :age " +
                            "WHERE u.id = :id")
                    .setParameter("name", name)
                    .setParameter("lastname", lastname)
                    .setParameter("age", age)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteUserById(long id) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.createQuery("DELETE FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
            em.close();
        } catch (Exception e) {
            if (tx != null) { tx.rollback(); }
            e.printStackTrace();
        }
    }
}
