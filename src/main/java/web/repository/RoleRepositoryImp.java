package web.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImp implements RoleRepository{

    @PersistenceContext(name = "persistenceUnit")
    private EntityManager entityManager;

    public List<Role> getRolesList() {
        List<Role> query = entityManager.createQuery("from Role ",Role.class).getResultList();
        return query;
    }

    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
