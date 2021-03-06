package web.repository;

import web.model.User;

import java.util.List;

public interface UserRepository {

    void createUser(User user);

    User readUser(long id);

    void updateUser(long id, User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    void deleteAllUsers();

    User getUserByName(String name);

}
