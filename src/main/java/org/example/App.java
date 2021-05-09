package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {

        /*
        Данное приложение поделено на две секции - в левой находится таблица со студентами,
        в правой - поля ввода для добавления новых студентов. Возможно, будет реализовано что-то еще.
         */

        Parent leftSection = FXMLLoader.load(getClass().getResource("left_section.fxml"));
        Parent rightSection = FXMLLoader.load(getClass().getResource("right_section.fxml"));

        //Помещаем компоненты в коробку
        HBox root = new HBox(leftSection, rightSection);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        stage.setTitle("Student DB");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(800);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}