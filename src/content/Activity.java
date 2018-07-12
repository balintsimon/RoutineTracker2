package content;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Main Activity class
 */
public class Activity implements Serializable {


    private static final long serialVersionUID = -8669535159068103889L;

    /**
     * Getters/Setters
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Variables/Properties
     */
    private LocalDate date;
    private String name, description;
    private Double duration;

    /**
     * Constructor gets all properties of the Activity
     */
    public Activity(LocalDate date, String name, double duration, String description){
        this.date = date;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
}
