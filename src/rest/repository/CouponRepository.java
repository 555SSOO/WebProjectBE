package rest.repository;

import rest.entities.CouponEntity;
import rest.entities.ShopEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom.
 * Obratite paznju da cemo ovde za projekat upisivati u fajl.
 */
public class CouponRepository {

    private static AtomicInteger currentIndex;

    static {
        if (!Repository.getIsConnected().get()) {
            Repository.initDB();
        }
        currentIndex = new AtomicInteger(0);
//        generateCoupons();
    }

    private static void generateCoupons() {
        for (int i = 0; i < 5; i++) {
            ShopEntity shopEntity = ShopRepository.getShops().get(i);
            CouponEntity coupon = new CouponEntity();
            coupon.setId((long) currentIndex.incrementAndGet());
            coupon.setShop(shopEntity);
            coupon.setProductName("Product " + i);
            coupon.setOriginalPrice(i * 3f);
            coupon.setDiscountedPrice(i * 2f);

//            java.util.Date currentDate = new java.util.Date();
//            java.util.Date yday = currentDate.

            coupon.setValidFrom(new Date((new java.util.Date()).getTime()));
            coupon.setValidTo(new Date((new java.util.Date()).getTime()));
            insertCoupon(coupon);
        }
    }


    public synchronized static CouponEntity insertCoupon(CouponEntity coupon) {
        String insertCoupon = "INSERT INTO COUPONS(NAME, DISCOUNTED_PRICE, ORIGINAL_PRICE, VALID_FROM, VALID_TO, SHOP) VALUES(?,?,?,?,?,?);";
        try {


            PreparedStatement preparedStatement = Repository.getConnection().prepareStatement(insertCoupon);
            preparedStatement.setString(1, coupon.getProductName());
            preparedStatement.setFloat(2, coupon.getDiscountedPrice());
            preparedStatement.setFloat(3, coupon.getOriginalPrice());
            preparedStatement.setDate(4, coupon.getValidFrom());
            preparedStatement.setDate(5, coupon.getValidTo());
            preparedStatement.setInt(6, Integer.valueOf(coupon.getShop().getId())); // The ID String must mach the ID integer in the database

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupon;
    }

    private static List<CouponEntity> getCoupons(String sql){
        List<CouponEntity> couponEntities = new ArrayList<>();
        try {
            Statement statement = Repository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                couponEntities.add(new CouponEntity((long) resultSet.getInt("COUPON_ID"), ShopRepository.getShopById(String.valueOf(resultSet.getInt("SHOP"))), resultSet.getString("NAME"), resultSet.getFloat("DISCOUNTED_PRICE"), resultSet.getFloat("ORIGINAL_PRICE"), resultSet.getDate("VALID_FROM"), resultSet.getDate("VALID_TO")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return couponEntities;
    }

    public synchronized static List<CouponEntity> getAllCoupons() {
        return getCoupons("SELECT * FROM COUPONS;");
    }

    public synchronized static List<CouponEntity> getActiveCoupons() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        return getCoupons("SELECT * FROM COUPONS WHERE VALID_FROM <= " + date + " AND VALID_TO >= " + date);
    }

    public synchronized static List<CouponEntity> getCouponsForShop(Integer id) {
        return getCoupons("SELECT * FROM COUPONS WHERE SHOP=" + id);
    }

    public synchronized static void deleteCoupon(int i) {
        String sql = "DELETE FROM COUPONS WHERE COUPON_ID=" + i;
        try {
            Statement statement = Repository.getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
