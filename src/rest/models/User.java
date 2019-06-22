package rest.models;

import lombok.Data;
import rest.constants.UserType;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7981291230196482123L;

    private String id;
    private String firstName;
    private String lastName;
    private UserType privilegeLevel;
    private String username;
    private String password;

}