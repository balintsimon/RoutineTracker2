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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainWindowController implements Initializable {
    private ArrayList<Activity> activities = new ArrayList<>();


    /**
     * Initialize 'File' Items
     */
    private File defaultSavePath = null;
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
        ObservableList<Activity> oActivities = FXCollections.observableArrayList(getActivities());
        tableView.setItems(oActivities);


    }


/**
 * ******************************************************File Menu******************************************************
 */
    /**
     * New Button Logic
     */
    public void newButtonPushed() {
        activities.clear();
        ObservableList<Activity> oActivities = FXCollections.observableArrayList(activities);
        tableView.setItems(oActivities);

    }

    /**
     * Load Button Logic
     */
    public void loadButtonPushed() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Database");

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("RDB files (*.rdb)", "*.rdb");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());

        if (file != null) {
            activities.clear();
            activities = loadFromFile(file);
            ObservableList<Activity> oActivities = FXCollections.observableArrayList(activities);
            tableView.setItems(oActivities);
            defaultSavePath = file;
        }
    }

    /**
     * Loading method
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Activity> loadFromFile(File file) {
        ArrayList<Activity> loadedActivities = null;


        try {
            ObjectInputStream in = new ObjectInputStream((new FileInputStream(file)));

            loadedActivities = (ArrayList<Activity>) in.readObject();
            System.out.println("Load completed");
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            System.out.println("Problem occured during deserialization");
            e.printStackTrace();
        }
        return loadedActivities;
    }

    /**
     * Save Button Logic
     */
    public void saveButtonPushed() {
        if (defaultSavePath != null)
            try {
                FileOutputStream fos = new FileOutputStream(defaultSavePath);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(activities);
                out.close();
                System.out.println("Save Completed");

            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            saveAsButtonPushed();
        }

    }

    /**
     * Save As Button Logic
     */
    public void saveAsButtonPushed() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Database");

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("RDB files (*.rdb)", "*.rdb");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());

        if (file != null) {
            saveToFile(activities, file);
            defaultSavePath = file;
        }
    }

    /**
     * Saving method
     */
    private void saveToFile(ArrayList<Activity> activities, File file) {
        FileOutputStream fos;
        ObjectOutputStream out;
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
     * Exit Button logic
     */
    public void exitButtonPushed() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * **************************************************Activity Menu**************************************************
     */
    /**
     * Add activity button logic
     * -Opens the AddActivity Window-
     */
    public void addActivityButtonPushed() throws IOException {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddActivityWindow.fxml"));

        Parent addActivityParent = loader.load();

        window.setTitle("Add an activity");
        Scene addActivityScene = new Scene(addActivityParent);
        AddActivityWindowController controller = loader.getController();
        controller.initData(getActivities(), tableView);
        window.setScene(addActivityScene);
        window.show();
    }

    public void removeActivityButtonPushed() {

    }


    /**
     * This method will return an ObservableList of People objects
     */
    private ArrayList<Activity> getActivities() {
        return activities;
    }
}