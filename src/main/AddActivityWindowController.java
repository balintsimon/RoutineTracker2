package main;

import content.Activity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddActivityWindowController implements Initializable {
    @FXML private Button closeButton;
    TableView<Activity> tableView;
    @FXML private DatePicker dateInput;
    @FXML private TextField nameInput;
    @FXML private TextField durationInput;
    @FXML private TextArea descriptionInput;



    public void initData(TableView<Activity> tableView){
        this.tableView = tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeButtonPushed(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void addButtonPushed(){
        Activity newActivity = new Activity(dateInput.getValue(), nameInput.getText(), Double.parseDouble(durationInput.getText()), descriptionInput.getText());
        tableView.getItems().add(newActivity);

    }
}
