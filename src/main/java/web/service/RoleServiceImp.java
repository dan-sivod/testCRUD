package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImp {

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }
    public List<Role> getRolesList() {
        return roleRepository.getRolesList();
    }
}
