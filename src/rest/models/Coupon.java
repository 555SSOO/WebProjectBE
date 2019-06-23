package rest.models;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * Model kupona cemo koristiti kao jedinicu transporta informacija
 * izmedju BE i FE. Potencijalno bi ga koristili i za medjuservisnu 
 * komunikaciju.
 * 
 * Upotrebljena je biblioteka lombok koja naravno nije obavezna.
 * Ovde demonstriram kako ce se sa @Data generisati svi adekvatni:
 * Geteri (Moze se postici sa @Getters)
 * Seteri (Moze se postici sa @Setters)
 * Prazan konstruktor (Moze se postici sa @NoArgsConstructor)
 * ToString metoda (Moze se postici sa @ToString).
 * 
 * Obratite paznju da ova klasa ima referencu na instancu Shop.
 * Ova instanca, ako je prisutna, ce takodje biti serijalizovana
 * u JSON od strane json-moxy.
 */
@Data
public class Coupon implements Serializable{
	
	private static final long serialVersionUID = 7981293420196482847L;
	private Long id;
	private Shop shop;
	private String productName;
	private Float discountedPrice;
	private Float originalPrice;
	private Float discount;
	private Date validFrom;
	private Date validTo;

}
