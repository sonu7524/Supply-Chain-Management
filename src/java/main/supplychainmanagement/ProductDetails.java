package main.supplychainmanagement;
import javafx.beans.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {

    public static TableView<Product> productTable;
    public static Pane getAllProducts(){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"Lenovo", 84537));
//        data.add(new Product(1,"HP", 18437));
        ObservableList<Product> products = Product.getAllProduct();

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width, SupplyChain.height);
        tablePane.setStyle("-fx-background-color: #87CEEB");
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public static Pane getProductsByName(String productName){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"Lenovo", 84537));
//        data.add(new Product(1,"HP", 18437));
        ObservableList<Product> products = Product.getProductByName(productName);

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width, SupplyChain.height);
        tablePane.setStyle("-fx-background-color: #87CEEB");
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
}
