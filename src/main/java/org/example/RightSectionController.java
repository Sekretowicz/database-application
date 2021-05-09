package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;

public class RightSectionController {

    //Константы
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String ADDITION_QUERY =
            "INSERT INTO STUDENTS (first_name, last_name, birth_date, s_group) VALUES (?, ?, ?, ?)";

    //FXML-компоненты
    @FXML
    TextField firstNameTextField;

    @FXML
    TextField lastNameTextField;

    @FXML
    DatePicker birthDatePicker;

    @FXML
    TextField groupTextField;

    @FXML
    void addStudent() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(ADDITION_QUERY);
            statement.setString(1, firstNameTextField.getText());
            statement.setString(2, lastNameTextField.getText());
            if (birthDatePicker.getValue()!=null) {
                statement.setDate(3, Date.valueOf(birthDatePicker.getValue()));
            } else {
                statement.setDate(3, null);
            }
            statement.setString(4, groupTextField.getText());

            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    }
}
