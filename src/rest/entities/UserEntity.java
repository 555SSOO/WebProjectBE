package rest.entities;

import lombok.Data;
import rest.constants.UserType;

@Data
public class UserEntity {

    private String id;
    private String firstName;
    private String lastName;
    private UserType privilegeLevel;
    private String username;
    private String password;

}
