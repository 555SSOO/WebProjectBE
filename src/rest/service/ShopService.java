package rest.service;

import rest.mappers.ShopMapper;
import rest.models.Shop;
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

}
