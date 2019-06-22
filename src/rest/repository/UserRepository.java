package rest.repository;

import rest.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    private static List<UserEntity> USER_LIST;

    static {
        USER_LIST = generateUsers();
    }

    private static List<UserEntity> generateUsers() {
        List<UserEntity> users = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(String.valueOf(i));

            // GENERATE USERS



            users.add(userEntity);
        }
        return users;
    }

    public synchronized static List<UserEntity> getUsers() {
        return USER_LIST;
    }

    public synchronized static UserEntity getUserById(Long index) {
        return USER_LIST.get(Math.toIntExact(index));
    }

}
