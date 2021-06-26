package web.repository;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext(name = "persistenceUnit")
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public User readUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(long id, User user) {
        User userToBeUpdated = readUser(id);
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setAge(user.getAge());
        entityManager.flush();
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(readUser(id));
        entityManager.flush();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> query = entityManager.createQuery("from User",User.class).getResultList();
        return query;
    }

    @Override
    public void deleteAllUsers() {
        List<User> usersList = getAllUsers();
        for(User user : usersList){
            entityManager.remove(user);
        }
        entityManager.flush();
    }

    @Override
    public User getUserByName(String name) {
        List<User> query = entityManager.createQuery("from User",User.class).getResultList();
        for(User user : query){
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }
}