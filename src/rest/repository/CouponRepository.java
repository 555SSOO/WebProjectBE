package rest.repository;

import rest.entities.CouponEntity;
import rest.entities.ShopEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom.
 * Obratite paznju da cemo ovde za projekat upisivati u fajl.
 */
public class CouponRepository {

	private static List<CouponEntity> COUPON_LIST;
	private static AtomicInteger currentIndex;

    static {
    	currentIndex = new AtomicInteger(0);
        COUPON_LIST = generateCoupons();
    }
    
	private static List<CouponEntity> generateCoupons() {
		List<CouponEntity> coupons = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			ShopEntity shopEntity = ShopRepository.getShops().get(i);
			CouponEntity coupon = new CouponEntity();
			coupon.setId((long) currentIndex.incrementAndGet());
			coupon.setShop(shopEntity);
			coupon.setProductName("Product " + i);
			coupon.setOriginalPrice(i * 3f);
			coupon.setDiscountedPrice(i * 2f);
			coupons.add(coupon);
		}

		return coupons;
	}

    public synchronized static List<CouponEntity> getCoupons(){
        return COUPON_LIST;
    }

    public synchronized static void deleteCoupon(int i){
    	for (CouponEntity couponEntity : COUPON_LIST) {
			if (couponEntity.getId() == i) {
				COUPON_LIST.remove(couponEntity);
				break;
			}
		}
        
    }

    public synchronized static CouponEntity addCoupon(CouponEntity couponEntity){

		couponEntity.setId((long) currentIndex.incrementAndGet());
        COUPON_LIST.add(couponEntity);
        return couponEntity;
    }

}
