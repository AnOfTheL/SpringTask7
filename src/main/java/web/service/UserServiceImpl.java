package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }
    @Override
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
    @Override
    public void updateUser(long id, String name, String lastName, byte age) {
        userDao.updateUser(id, name, lastName, age);
    }
    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }
}
