package rest.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Shop implements Serializable {

    private static final long serialVersionUID = 4887390123909407000L;
    private Long id;
    private String name;

}
