package web.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;
    private EntityTransaction tx = null;

    @Override
    public void createUsersTable() {
        em.createNativeQuery("" +
                        "CREATE TABLE IF NOT EXISTS users ( " +
                        "id SERIAL PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "lastname VARCHAR(255), " +
                        "age SMALLINT);" +
                        "")
                .executeUpdate();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        em.joinTransaction();
        em.persist(user);
        em.flush();
    }

    @Override
    public void dropUsersTable() {
        em.createNativeQuery("DROP TABLE IF EXISTS users;")
                .executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery(
                "SELECT u FROM User u", User.class)
                .getResultList();
    }
    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(long id, String name, String lastname, byte age) {
        em.joinTransaction();

        User user = em.find(User.class, id);
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);

        em.flush();
    }

    @Override
    public void deleteUserById(long id) {
        em.joinTransaction();
        User user = em.find(User.class, id);
        em.remove(user);
        em.flush();
    }
}
