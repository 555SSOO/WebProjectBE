package repository;

import rest.entities.ShopEntity;

import java.util.ArrayList;
import java.util.List;

public class ShopRepository {

    private static List<ShopEntity> SHOP_LIST;

    static {
        SHOP_LIST = generateShops();
    }

    private static List<ShopEntity> generateShops() {
        List<ShopEntity> shops = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            ShopEntity shopEntity = new ShopEntity();
            shopEntity.setId((long) i);
            shopEntity.setName("Shop " + i);
            shops.add(shopEntity);
        }
        return shops;
    }

    public synchronized static List<ShopEntity> getShops() {
        return SHOP_LIST;
    }

    public synchronized static ShopEntity getShopById(Long index) {
        return SHOP_LIST.get(Math.toIntExact(index));
    }

}
