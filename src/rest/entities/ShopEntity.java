package rest.entities;

import lombok.Data;

@Data
public class ShopEntity {

    public ShopEntity(){}

    public ShopEntity(String _id, String _name){
        this.id = _id;
        this.name = _name;
    }

    private String id;
    private String name;
}
