package rest.mappers;


import rest.entities.ShopEntity;
import rest.models.Shop;

import java.util.List;
import java.util.stream.Collectors;

public class ShopMapper {

    public ShopEntity mapToEntity(Shop shop) {
        if (shop == null) {
            return null;
        }

        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shop.getId());
        shopEntity.setName(shop.getName());
        return shopEntity;
    }

    public Shop mapToModel(ShopEntity shopEntity) {
        if (shopEntity == null) {
            return null;
        }

        Shop shop = new Shop();
        shop.setId(shopEntity.getId());
        shop.setName(shopEntity.getName());
        return shop;
    }

    public List<Shop> mapToModelList(List<ShopEntity> shopEntities) {
        return shopEntities.stream().map(this::mapToModel).collect(Collectors.toList());
    }
}
