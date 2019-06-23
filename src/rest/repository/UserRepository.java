package rest.repository;

import rest.constants.UserType;
import rest.entities.CouponEntity;
import rest.entities.ShopEntity;
import rest.entities.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    static {
        if (!Repository.getIsConnected().get()) {
            Repository.initDB();
        }
//        generateUsers();
    }

    private static void generateUsers() {
        for (int i = 0; i <= 5; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(String.valueOf(i));

            userEntity.setFirstName("FirstName" + i);
            userEntity.setLastName("LastName" + i);
            userEntity.setUsername("Username" + i);
            userEntity.setPassword("Password" + i);
            userEntity.setPrivilegeLevel(UserType.ADMINISTRATOR);

            insertUser(userEntity);
        }

    }

    public synchronized static UserEntity insertUser(UserEntity userEntity) {
        String insertUser = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, PRIVILEGE_LEVEL) VALUES(?,?,?,?,?);";
        try {

            PreparedStatement preparedStatement = Repository.getConnection().prepareStatement(insertUser);
            preparedStatement.setString(1, userEntity.getFirstName());
            preparedStatement.setString(2, userEntity.getLastName());
            preparedStatement.setString(3, userEntity.getUsername());
            preparedStatement.setString(4, userEntity.getPassword());
            preparedStatement.setInt(5, userEntity.getPrivilegeLevel().ordinal());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    public synchronized static UserEntity getUser(String username, String password) {

        String getUser = "SELECT * FROM USERS WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "';";
        try {
            Statement statement = Repository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getUser);

            return new UserEntity(resultSet.getString("FIRST_NAME"), resultSet.getString("LAST_NAME"), UserType.values()[resultSet.getInt("PRIVILEGE_LEVEL")]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
