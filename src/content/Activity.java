package content;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Activity {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getDuration() {
        return duration.get();
    }

    public SimpleDoubleProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    private SimpleStringProperty name, description;
    private SimpleDoubleProperty duration;

    public Activity(LocalDate date, String name, double duration, String description){
        this.date = date;
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.duration = new SimpleDoubleProperty(duration);
    }
}
