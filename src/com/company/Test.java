package com.company;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Test extends Application {
    @Override
    public void start(Stage stage) {
        //Creating a Button
        Button button = new Button();
        //Setting text to the button
        button.setText("Sample Button");
        //Setting the location of the button
        button.setTranslateX(150);
        button.setTranslateY(60);
        //Setting the stage
        Group root = new Group(button);
        Scene scene = new Scene(root, 595, 150, Color.BEIGE);
        stage.setTitle("Button Example");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
