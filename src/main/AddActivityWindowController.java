package main;

import content.Activity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddActivityWindowController implements Initializable {
    /**
     * Initialize a tableView, so the addButton can make a new Activity in it
     */
    ArrayList<Activity> activities;
    @FXML private Button closeButton;
    private TableView<Activity> tableView;
    @FXML private DatePicker dateInput;
    @FXML private TextField nameInput;
    @FXML private TextField durationInput;
    @FXML private TextArea descriptionInput;

    /**
     * Get the original tableView
     */
    public void initData(ArrayList<Activity> activities, TableView<Activity> tableView){
        this.activities = activities;
        this.tableView = tableView;
    }

    /**
     * Initializes bindable properties
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Close this window
     */
    public void closeButtonPushed(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Add a new Activity to the table
     */
    public void addButtonPushed(){
        Activity newActivity = new Activity(dateInput.getValue(), nameInput.getText(), Double.parseDouble(durationInput.getText()), descriptionInput.getText());
        activities.add(newActivity);
        System.out.println("Added");
        ObservableList<Activity> oActivities = FXCollections.observableArrayList(activities);
        tableView.setItems(oActivities);
    }
}
