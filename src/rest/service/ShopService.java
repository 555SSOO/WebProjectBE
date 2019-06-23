package rest.service;

import rest.entities.CouponEntity;
import rest.entities.ShopEntity;
import rest.mappers.ShopMapper;
import rest.models.Coupon;
import rest.models.Shop;
import rest.repository.CouponRepository;
import rest.repository.ShopRepository;

import java.util.List;

public class ShopService {

    private final ShopMapper shopMapper;

    public ShopService() {
        this.shopMapper = new ShopMapper();
    }

    public List<Shop> getShops() {
        return shopMapper.mapToModelList(ShopRepository.getShops());
    }

    public Shop addShop(Shop shop) {
        ShopEntity shopEntity = shopMapper.mapToEntity(shop);
        return shopMapper.mapToModel(ShopRepository.insertShop(shopEntity));
    }

}
