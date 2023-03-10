package main.supplychainmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {

    public static final int width = 700, height = 600, headerBar = 50;

    public static Pane bodyPane = new Pane();

    public static int bodyWidth, bodyHeight;

    Login login = new Login();
    Label messageLabel = new Label();
    ProductDetails productDetails = new ProductDetails();
    Button globalLoginButton;
    Label customerNameLabel = null;
    String customerName = null;
    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                productDetails.getProductsByName(productName);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(productName));
            }
        });

        globalLoginButton = new Button("Login");
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                globalLoginButton.setDisable(true);

            }
        });

        customerNameLabel = new Label();

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar);

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: #87CEEB");

        gridPane.add(searchText, 2, 0);
        gridPane.add(searchButton, 3, 0);
        gridPane.add(globalLoginButton, 0, 0);
        gridPane.add(customerNameLabel, 0, 0);

        return gridPane;
    }

    private GridPane loginPage(){
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label accountLogin = new Label("ACCOUNT-LOGIN");

        accountLogin.setFont(Font.font("Arial", FontWeight.BOLD,20));


        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();


        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                String name = "";
                if(login.customerLogin(email, password)){
                    messageLabel.setText("Login Successful");
                    customerName = name;
                    globalLoginButton.setDisable(true);
                    customerNameLabel.setText("Welcome : "+ customerName);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }
                else messageLabel.setText("Something went wrong, Unable to Login");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color: #87CEEB");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(accountLogin,1,0);
        gridPane.add(messageLabel, 1, 1);
        gridPane.add(emailTextField,1,2);
        gridPane.add(emailLabel,0,2);
        gridPane.add(passwordField,1,3);
        gridPane.add(passwordLabel,0,3);
        gridPane.add(loginButton,0,4);
        return gridPane;
    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height+headerBar);
        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().add(loginPage());
        root.getChildren().addAll(headerBar(),bodyPane);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}