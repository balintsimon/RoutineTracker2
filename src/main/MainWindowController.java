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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class MainWindowController implements Initializable {
    ArrayList<Activity> activities = new ArrayList<>();
    ObservableList<Activity> oActivities = FXCollections.observableArrayList(getActivities());

    /**
     * Initialize 'File' Items
     */
    @FXML private MenuItem newButton;
    @FXML private MenuItem loadButton;
    @FXML private MenuItem saveButton;
    @FXML private MenuItem saveAsButton;
    @FXML private MenuItem exitButton;
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
        tableView.setItems(oActivities);
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
        controller.initData(oActivities);
        window.setScene(addActivityScene);
        window.show();
    }

    /**
     * Load Button Logic
     */
    public void loadButtonPushed(){
        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Database");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.rdb"));

            File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());


        });
    }

    /**
     * Save Button Logic
     */
    public void saveButtonPushed() {
        String filename = "savedDatabase";
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(activities);
            out.close();
            System.out.println("Save Completed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsButtonPushed(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Database");

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("RDB files (*.rdb)", "*.rdb");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());

        if (file != null) {
            saveToFile(activities, file);
        }
    }

    /**
     * Saving method
     */
    private void saveToFile(ArrayList<Activity> activities, File file) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(activities);
            out.close();
            System.out.println("Save Completed");

        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private ArrayList<Activity> getActivities() {
        activities.add(new Activity(LocalDate.now(), "Dili", 2, "Adéelltjeakheee"));
        activities.add(new Activity(LocalDate.of(2018, Month.DECEMBER, 30), "Lafitykesz", 1, "bohohohohoroorororr"));
        activities.add(new Activity(LocalDate.of (2018, Month.JULY, 31), "bodula$h", 100, "blueblekthbahtee"));
        return activities;
    }
}