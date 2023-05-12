package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Component
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    public List<User> printUsers(){
        //userDao.dropUsersTable();
        //userDao.createUsersTable();
        //userDao.saveUser("name", "lastName", (byte) 21);
        return userDao.getAllUsers();
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public void updateUser(long id, String name, String lastName, byte age) {
        userDao.updateUser(id, name, lastName, age);
    }

    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }
}
