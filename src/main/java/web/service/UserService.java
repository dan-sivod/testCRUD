package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void createUser(User user);

    User readUser(long id);

    void updateUser(long id,User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    void deleteAllUsers();

    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
}
