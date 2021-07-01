package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImp  implements  RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepo(RoleRepository roleRepo) {
        this.roleRepository = roleRepo;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public List<Role> getRolesList() {
        return roleRepository.getRolesList();
    }
}
