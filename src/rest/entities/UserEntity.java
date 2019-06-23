package rest.entities;

import lombok.Data;
import rest.constants.UserType;

@Data
public class UserEntity {

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, UserType privilegeLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.privilegeLevel = privilegeLevel;
    }

    private String id;
    private String firstName;
    private String lastName;
    private UserType privilegeLevel;
    private String username;
    private String password;

}
