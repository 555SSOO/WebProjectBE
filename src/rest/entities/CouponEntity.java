package rest.entities;

import lombok.Data;

import java.sql.Date;


/**
 * Model kupona cemo koristiti kao jedinicu transporta informacija
 * izmedju Web Aplikacije i Baze.
 *
 * Podaci iz baze (MySQL, Mongo, Text fajl) ce se mapirati na polja
 * instance ove klase ako su vezani za ovaj domen (Kupon).
 */
@Data
public class CouponEntity {

    public CouponEntity() {
    }

    public CouponEntity(Long id, ShopEntity shop, String productName, Float discountedPrice, Float originalPrice, Date validFrom, Date validTo) {
        this.id = id;
        this.shop = shop;
        this.productName = productName;
        this.discountedPrice = discountedPrice;
        this.originalPrice = originalPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    private Long id;
    private ShopEntity shop;
    private String productName;
    private Float discountedPrice;
    private Float originalPrice;
    private Date validFrom;
    private Date validTo;
}
