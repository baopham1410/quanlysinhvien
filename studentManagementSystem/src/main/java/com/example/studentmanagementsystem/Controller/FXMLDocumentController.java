package com.example.studentmanagementsystem.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.studentmanagementsystem.Model.DataDriver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Quoc Bao
 */
public class FXMLDocumentController implements Initializable {


    public Button close;
    @FXML
    private Button loginBtr;

    @FXML
    private AnchorPane main_from;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    public void close(){
        System.exit(0);
    }


    public void ShowAlert (String s ){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    private double x= 0;
    private double y=0;
    public void loginAdmin(){

        DataDriver d1 = new DataDriver();
        ResultSet result = d1.getClientData();
        if(username.getText().isEmpty()|| password.getText().isEmpty()){
           ShowAlert("enter a valid value username or password");
        }else {
            try {
                while (result.next()){
                    if(password.getText().equals(result.getString("password"))&& username.getText().equals(result.getString("username"))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login!");
                        alert.showAndWait();
                        loginBtr.getScene().getWindow().hide();
                        try{
                            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Dashboard.fxml"));
                            System.out.println("hehe");
                            Scene scene;
                            scene = new Scene(root);
                            Stage stage = new Stage();
                            root.setOnMousePressed((MouseEvent event)->{
                                x = event.getSceneX();
                                y = event.getScreenY();
                            });
                            root.setOnMouseDragged((MouseEvent event)->{
                                stage.setX(event.getScreenX()-x);
                                stage.setY(event.getScreenY()-y);
                            });

                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.setScene(scene);
                            stage.show();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }else{
                        ShowAlert("Error username/password");
                    }
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginBtr.setOnAction(event  -> loginAdmin());
    }
}
