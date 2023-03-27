package sample;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PanelController {
    public void add(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_custom");
    }

    public void cal(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("calculation");
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
