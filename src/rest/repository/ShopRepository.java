package rest.repository;

import rest.entities.ShopEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopRepository {

    static {
        if(!Repository.getIsConnected().get()){
            Repository.initDB();
        }
//        generateShops();
    }

    private static List<ShopEntity> generateShops() {
        List<ShopEntity> shops = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            ShopEntity shopEntity = new ShopEntity();
            shopEntity.setId(String.valueOf(i));
            shopEntity.setName("Shop " + i);

            insertShop(shopEntity);

        }
        return shops;
    }

    public static ShopEntity insertShop(ShopEntity shop) {
        String insertShop = "INSERT INTO SHOPS(NAME) VALUES(?);";
        try {

            PreparedStatement preparedStatement = Repository.getConnection().prepareStatement(insertShop);
            preparedStatement.setString(1, shop.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    public synchronized static List<ShopEntity> getShops() {
        String getAllShops = "SELECT * FROM SHOPS;";
        List<ShopEntity> shopEntities = new ArrayList<>();
        try {
            Statement statement = Repository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getAllShops);
            while(resultSet.next()){
                shopEntities.add(new ShopEntity(String.valueOf(resultSet.getInt("SHOP_ID")), resultSet.getString("NAME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shopEntities;
    }

    public synchronized static ShopEntity getShopById(String index) {

        String getShopsById = "SELECT * FROM SHOPS WHERE SHOP_ID = " + index;
        try {
            Statement statement = Repository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getShopsById);

            return new ShopEntity(String.valueOf(resultSet.getInt("SHOP_ID")), resultSet.getString("NAME"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
