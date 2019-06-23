package rest.service;

import rest.entities.CouponEntity;
import rest.entities.ShopEntity;
import rest.entities.UserEntity;
import rest.mappers.UserMapper;
import rest.models.Coupon;
import rest.models.User;
import rest.repository.CouponRepository;
import rest.repository.ShopRepository;
import rest.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserMapper userMapper;

    public UserService() {
        this.userMapper = new UserMapper();
    }

    public List<User> getUsers() {
//        return userMapper.mapToModelList(UserRepository.getUsers());
    return null;
    }


    public User addUser(User user) {
        UserEntity userEntity = userMapper.mapToEntity(user);
        return userMapper.mapToModel(UserRepository.insertUser(userEntity));
    }

    public User getUser(String username, String password) {
        return userMapper.mapToModel(UserRepository.getUser(username, password));
    }

}
