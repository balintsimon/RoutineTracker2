package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutWindowController implements Initializable {
    private Image kidIcon;
    @FXML private ImageView iconView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kidIcon  = new Image("file:kidicon.jpg", 50, 50, false, false);
        iconView = new ImageView();

        iconView.setImage(kidIcon);
        iconView.setSmooth(true);
        iconView.setCache(true);
    }
}
