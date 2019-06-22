package rest.mappers;

import rest.entities.CouponEntity;
import rest.models.Coupon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ova klasa pruza rudimentalnu funkcionalnost transformacije
 * iz modela u entitete.
 *
 * Istraziti MapStruct! Odlicna bibilioteka koja zavrsava ovaj posao za vas.
 *
 * Primetite da dinamicko generisanje popusta u procentima radimo ovde.
 *
 * Takodje posto Kupon ima u sebi referencu na Shop, koristimo ShopMapper
 * da bi i njega transformisali.
 */
public class CouponMapper {

    private final ShopMapper shopMapper = new ShopMapper();

    public Coupon mapToModel(CouponEntity couponEntity) {

        if (couponEntity == null) {
            return null;
        }

        Coupon coupon = new Coupon();
        coupon.setId(couponEntity.getId());
        coupon.setProductName(couponEntity.getProductName());
        coupon.setDiscountedPrice(couponEntity.getDiscountedPrice());
        coupon.setOriginalPrice(couponEntity.getOriginalPrice());
        coupon.setShop(shopMapper.mapToModel(couponEntity.getShop()));
        coupon.setDiscount(calculateDiscount(couponEntity.getOriginalPrice(), couponEntity.getDiscountedPrice()));
        coupon.setValidFrom(couponEntity.getValidFrom());
        coupon.setValidTo(couponEntity.getValidTo());

        return coupon;
    }


    private Float calculateDiscount(Float oldPrice, Float newPrice) {
        if(newPrice.equals(oldPrice)){
            return 0f;
        }

        if(newPrice == 0){
            return 100f;
        }

        return (100 - (newPrice * 100 / oldPrice)) * -1;
    }

    public List<Coupon> mapToModelList(List<CouponEntity> couponEntities) {
        if (couponEntities == null) {
            return null;
        }
        return couponEntities.stream().map(this::mapToModel).collect(Collectors.toList());
    }

    public CouponEntity mapToEntity(Coupon coupon){
        if (coupon == null) {
            return null;
        }

        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(coupon.getId());
        couponEntity.setProductName(coupon.getProductName());
        couponEntity.setDiscountedPrice(coupon.getDiscountedPrice());
        couponEntity.setOriginalPrice(coupon.getOriginalPrice());
        couponEntity.setValidFrom(coupon.getValidFrom());
        couponEntity.setValidTo(coupon.getValidTo());

        return couponEntity;
    }
}
