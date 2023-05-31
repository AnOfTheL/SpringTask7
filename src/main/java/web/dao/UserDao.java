package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    void deleteUserById(long id);

    List<User> getAllUsers();

    void updateUser(long id, String name, String lastname, byte age);

    User getUserById(long id);
}
