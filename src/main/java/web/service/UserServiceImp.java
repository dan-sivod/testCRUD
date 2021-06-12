package web.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void createUser(User user){
        userRepository.createUser(user);
    }

    public User readUser(long id){
        return userRepository.readUser(id);
    }

    public void updateUser(long id, User user){
        userRepository.updateUser(id, user);
    }

    public void deleteUser(long id){
        userRepository.deleteUser(id);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public void deleteAllUsers(){
        userRepository.deleteAllUsers();
    }
}
