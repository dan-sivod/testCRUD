package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.repository.UserRepository;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepositoryImp){
        userRepository = userRepositoryImp;
    }

    @Override
    public void createUser(User user){
        userRepository.createUser(user);
    }

    @Override
    public User readUser(long id){
        return userRepository.readUser(id);
    }

    @Override
    public void updateUser(long id, User user){
        userRepository.updateUser(id, user);
    }

    @Override
    public void deleteUser(long id){
        userRepository.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    @Override
    public void deleteAllUsers(){
        userRepository.deleteAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getUserByName(s);
    }
}
