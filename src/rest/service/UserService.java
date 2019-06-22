package rest.service;

import rest.mappers.UserMapper;
import rest.models.User;
import rest.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserMapper userMapper;

    public UserService() {
        this.userMapper = new UserMapper();
    }

    public List<User> getUsers() {
        return userMapper.mapToModelList(UserRepository.getUsers());
    }

}
