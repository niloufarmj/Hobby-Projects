package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        PageLoader.initStage(stage);
        new PageLoader().load("panel");
    }



    public static void main(String[] args) {
        launch();
    }

    /*@Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file
    }*/
}
