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
//@EnableTransactionManagement
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext(name = "TDPersistenceUnit")
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
    public void updateUser(User user) {
        entityManager.merge(user);
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
}