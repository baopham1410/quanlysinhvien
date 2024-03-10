package com.example.studentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentManagementSystem extends Application {

    public static void main(String[] args) {
        launch(args);
    }
   private double x=0;
    private double y=0;

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event)->{
                x =event.getSceneX();
                y= event.getSceneY();
                stage.setOpacity(.8);

            });
            root.setOnMouseReleased((MouseEvent event)->{
                stage.setOpacity(1);
            });
            root.setOnMouseDragged((MouseEvent event)->{
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

            });
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
