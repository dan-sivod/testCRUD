package web.service;

import org.springframework.stereotype.Service;
import web.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    List<Role> getRolesList();

    Role getRoleById(Long id);
}
