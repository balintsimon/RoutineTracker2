package main;


import content.Activity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {


    /**
     * Configure the table
     */
    @FXML private TableView<Activity> tableView;
    @FXML private TableColumn<Activity, LocalDate> dateColumn;
    @FXML private TableColumn<Activity, String> nameColumn;
    @FXML private TableColumn<Activity, Double> durationColumn;
    @FXML private TableColumn<Activity, String> descriptionColumn;

    /**
     * Initilalizes bindable properties
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up the columns in the table
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        //load dummy data
        tableView.setItems(getActivities());
    }

    /**
     * Opens the AddActivity Window
     */
    public void addActivityButtonPushed() throws IOException {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddActivityWindow.fxml"));

        Parent addActivityParent = loader.load();

        window.setTitle("Add an activity");
        Scene addActivityScene = new Scene(addActivityParent);
        AddActivityWindowController controller = loader.getController();
        controller.initData(tableView);
        window.setScene(addActivityScene);
        window.show();
    }

    /**
     * Exits the application
     */
    public void exitButtonPushed(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * This method will return an ObservableList of People objects
     */
    private ObservableList<Activity> getActivities() {
        ObservableList<Activity> activities = FXCollections.observableArrayList();
        activities.add(new Activity(LocalDate.now(), "Dili", 2, "Adéelltjeakheee"));
        activities.add(new Activity(LocalDate.of(2018, Month.DECEMBER, 30), "Lafitykesz", 1, "bohohohohoroorororr"));
        activities.add(new Activity(LocalDate.of (2018, Month.JULY, 31), "bodula$h", 100, "blueblekthbahtee"));

        return activities;
    }
}