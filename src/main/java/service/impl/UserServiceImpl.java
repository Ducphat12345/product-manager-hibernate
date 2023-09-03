package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
