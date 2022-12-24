package main.supplychainmanagement;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }
    //constructor for assigning the values
    public Product(int id, String name, double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProduct(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProducts = "SELECT * FROM PRODUCT";
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectProducts);
            while(rs.next()){
                productList.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name") ,
                        rs.getDouble("price")
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
    public static ObservableList<Product> getProductByName(String productName){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProducts = String.format("SELECT * FROM PRODUCT WHERE lower(name) like '%%%s%%'",productName.toLowerCase());
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectProducts);
            while(rs.next()){
                productList.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name") ,
                        rs.getDouble("price")
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
