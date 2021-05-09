package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.sql.*;

/*
    Класс-контроллер для функционала в левой части экрана - таблица со студентами и кнопка обновления.
 */

public class LeftSectionController {
    //Константы
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String SELECT_QUERY =
            "SELECT s_id, first_name, last_name, birth_date, s_group FROM students";
    private static final String DELETE_QUERY = "DELETE FROM students WHERE s_id=?";

    //Список студентов для таблицы
    private ObservableList<Student> studentsList;

    //FX-компоненты
    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, Integer> idColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, LocalDate> birthDateColumn;

    @FXML
    private TableColumn<Student, String> groupColumn;

    @FXML
    private Button refreshButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void refreshTable () {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_QUERY);

             studentsList.clear();

            while (rs.next()) {
                /*
                Если дата рождения не указана, сразу будем передавать null во
                избежание исключения, т.к. нам нужно форматировать sql.Date в LocalDate
                 */
                LocalDate birthDate = null;
                if (rs.getDate(4)!=null) {
                    birthDate = rs.getDate(4).toLocalDate();
                }
                Student student = new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        birthDate,
                        rs.getString(5));
                studentsList.add(student);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void deleteSelected () {
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, selectedStudent.getId());
            statement.execute();
            refreshTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        firstNameColumn.setEditable(true);
        studentsTable.setEditable(true);
        studentsList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("birthDate"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));

        refreshTable();
        studentsTable.setItems(studentsList);
    }

}
